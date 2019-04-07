package io.github.hotlava03.baclava.commands.util;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RandomCmd extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot())
            return;
        Message message = event.getMessage();
        String content = message.getContentRaw();
        String prefix = ">>";
        if (content.equals(prefix + "shuffle") || content.equals(prefix + "shuffle help"))
            event.getChannel().sendMessage("**Shuffle:**\n`>>shuffle <word1> <word2> [other words]`\n<> - Required Argument\n[] - Optional Argument\n\n**Description:** Shuffle a list of words, seperated by space (' ').").queue();

        else if (content.startsWith(prefix + "shuffle ")) {
            MessageChannel channel = event.getChannel();
            int beginIndex = content.indexOf(">>");
            int endIndex = content.indexOf(" ");
            String replacement = "";
            String toBeReplaced = content.substring(beginIndex, 1 + endIndex);
            content = content.replace(toBeReplaced, replacement);
            String[] splitContent = content.split("\\s");
            List<String> list = Arrays.asList(splitContent);
            Collections.shuffle(list);
            Object[] randomArray = list.toArray();
            int i = 1;
            String newContent = "Something went wrong [RandomCmd-1]"; //This will be changed if the for runs. This is to initialize the String. If the for isn't executed, an error message will appear in the embed, which is what is written in newContent initially.
            for (Object o : randomArray) {
                if (i == 1)
                    newContent = "";
                if (i == randomArray.length) {
                    newContent = newContent + o;
                    break;
                }
                newContent = newContent + o + "; ";
                i++;
            }
            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("**Shuffled Content**", null);
            embed.setColor(Color.orange);
            embed.setDescription(newContent);
            try {
                channel.sendMessage(embed.build()).queue();
            } catch (Exception e) {
                if (event.getChannel().getName().contains("bot") || event.getChannel().getName().contains("command"))
                    channel.sendMessage("**Failed to send message: No embed links permission**").queue();
                else if (e.toString().toLowerCase().contains("embed")) {
                    try {
                        message.delete().queue();
                    } catch (Exception exc) {
                        channel.sendMessage("**No manage messages permission.**").queue();
                    }
                    channel.sendMessage("<@" + event.getAuthor().getId() + ">, **Use this in a bot commands channel instead.**\n*Is this a bot command channel? For me to detect that add in its name the word 'bot' or 'command'(s).*").queue(messageDelete -> messageDelete.delete().queueAfter(10, TimeUnit.SECONDS));
                }
            }
        }
    }
}
