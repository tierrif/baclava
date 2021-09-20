package io.github.hotlava03.baclava.bot.commands

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.*
import net.dv8tion.jda.api.requests.RestAction
import net.dv8tion.jda.api.requests.restaction.MessageAction
import java.util.function.Consumer

class CommandEvent(
    val jda: JDA,
    val channel: MessageChannel,
    val channelType: ChannelType,
    val guild: Guild?,
    val isFromGuild: Boolean,
    val messageId: String,
    val messageIdLong: Long,
    val privateChannel: PrivateChannel?,
    val textChannel: TextChannel?,
    val message: Message,
    val author: User,
    val member: Member?,
    val args: Array<String>,
) {
    fun reply(text: CharSequence, callback: Consumer<Message>? = null) {
        val txt = text.replace(jda.token.toRegex(), "censored")
        channel.sendMessage(txt).queue(callback)
    }

    fun reply(embed: MessageEmbed, callback: Consumer<Message>? = null) {
        channel.sendMessage(embed).queue(callback)
    }

    fun reply(message: Message, callback: Consumer<Message>? = null) {
        channel.sendMessage(message).queue(callback)
    }
}