package io.github.hotlava03.baclava.ai

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.*
import java.math.BigInteger
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.Charset
import java.security.MessageDigest
import kotlin.coroutines.CoroutineContext

private const val USER_AGENT =
    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.85 Safari/537.36"

/**
 * This code is a migration from the original cleverbot-free package for node.js.
 * The original idea can be found here: https://github.com/IntriguingTiles/cleverbot-free/blob/master/index.js.
 */
internal class CleverBotWrapper(private val userData: UserData) : CoroutineScope {
    // List of cookies.
    private lateinit var cookies: List<Cookie>
    private lateinit var cbsid: String

    // Initialize Coroutine context.
    private var job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job

    // Initialize HTTP client and install timeout.
    private val client = HttpClient(CIO) {
        install(HttpTimeout)
    }

    /**
     * @param stimulus The message to send.
     * @param context (Optional) List of previous messages to send.
     *
     * @return CleverBot's response or null if an error has occurred.
     */
    suspend fun makeRequest(stimulus: String, context: List<String> = listOf()): String? {
        // Verify if cookies are initialized.
        if (!::cookies.isInitialized) {
            // Retrieve the XVIS cookie which is necessary to perform requests.
            val req = client.request<HttpResponse> {
                url("https://www.cleverbot.com/")
                method = HttpMethod.Get
                userAgent(USER_AGENT)
            }

            // Initialize the cookies.
            cookies = req.setCookie()
        }

        val encoded = escape(stimulus)
        var payload = "stimulus=${
            if (encoded.contains("%u"))
                escape(encoded.replace("%u", "|")) else encoded
        }&"

        // Assuming the first element is the first message sent.
        val reverseContext = context.reversed()

        // Iterate context and add the URI variable vTextX for each message.
        reverseContext.forEachIndexed { i, el ->
            val rEncoded = escape(el)
            payload += "vText${i + 2}=${
                if (rEncoded.contains("%u"))
                    escape(rEncoded.replace("%u", "|")) else rEncoded
            }&"
        }

        // Options.
        payload += "cb_settings_scripting=no&islearning=1&icognoid=wsf&icognocheck="
        payload += md5(payload.substring(7, 33))

        val cbsidStr = if (::cbsid.isInitialized) "&out=&in=&bot=c&cbsid=${cbsid}&xai=${
            cbsid.substring(0, 3)
        }&ns=1&al=&dl=&flag=&user=&mode=1&alt=0&reac=&emo=&sou=website&xed=&" else ""
        for (i in 0..15) {
            try {
                val res = client.request<String> {
                    url("https://www.cleverbot.com/webservicemin?uc=UseOfficialCleverbotAPI${cbsidStr}")
                    timeout {
                        connectTimeoutMillis = 10000
                        requestTimeoutMillis = 60000
                    }

                    val c = cookies[0]
                    cookie(
                        c.name,
                        c.value,
                        c.maxAge,
                        c.expires,
                        c.domain,
                        c.path,
                        c.secure,
                        c.httpOnly,
                        c.extensions,
                    )

                    userAgent(USER_AGENT)
                    body = payload
                    method = HttpMethod.Post
                }

                cbsid = res.split("\r")[1]
                return URLDecoder.decode(res.split("\r")[0], Charset.defaultCharset())
            } catch (e: Exception) {
                // Retry after a second.
                delay(1000)
            }
        }

        return null
    }

    private fun escape(str: String): String {
        return URLEncoder.encode(str, Charset.defaultCharset())
    }

    private fun md5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}