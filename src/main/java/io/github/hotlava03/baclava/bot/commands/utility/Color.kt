package io.github.hotlava03.baclava.bot.commands.utility

import io.github.hotlava03.baclava.bot.commands.Command
import io.github.hotlava03.baclava.bot.commands.CommandEvent
import okhttp3.internal.toHexString
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO

// Because Color is this class' name.
typealias AwtColor = java.awt.Color

const val INVALID_COLOR_MSG = "**Invalid color.** Examples:\n**HEX:** #FF00FF\n**RGB** 255,0,255\n" +
        "*Do not include spaces in RGB.*"

class Color : Command() {

    init {
        name = "color"
        category = Category.UTILITY
        description = "Visualize a color."
        aliases = arrayOf("getcolor", "colour", "getcolour")
        usage = "<hex|rgb>"
        minArgs = 1
    }

    override fun onCommand(e: CommandEvent) {
        val input = e.args[0]
        lateinit var color: AwtColor
        if (!input.startsWith("#")) {
            val rgb = input.split(",")
            if (rgb.size != 3) return e.reply(INVALID_COLOR_MSG)

            val (red, green, blue) = rgb
            color = AwtColor(red.toInt(), green.toInt(), blue.toInt())
        } else {
            if (input.length != 7) return e.reply(INVALID_COLOR_MSG)
            color = AwtColor.decode(input)
        }

        // Create image with 2D graphics.
        val bufferedImage = BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB)
        val g2d = bufferedImage.createGraphics()
        g2d.color = color
        g2d.fillRect(0, 0, 100, 100)
        g2d.dispose()

        // Convert to byte array.
        val out = ByteArrayOutputStream()
        ImageIO.write(bufferedImage, "jpg", out)

        e.channel.sendMessage("**HEX:** #${color.rgb.toHexString().substring(2)} - " +
                "**RGB**: ${color.red},${color.green},${color.blue}")
            .addFile(out.toByteArray(), "color.jpg")
            .queue()
    }
}
