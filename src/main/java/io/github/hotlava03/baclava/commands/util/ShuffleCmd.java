package io.github.hotlava03.baclava.commands.util;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ShuffleCmd extends Command {
    public ShuffleCmd(){
        this.name = "shuffle";
    }
    private EmbedBuilder createShuffleEmbed(String newContent){
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("**Shuffled Content**", null);
        embed.setColor(Color.orange);
        embed.setDescription(newContent);
        return embed;
    }
    private void shuffleSendCatch(MessageChannel channel, Message message, User author, String newContent){
        try {
            channel.sendMessage(createShuffleEmbed(newContent).build()).queue();
        } catch (Exception e) {
            if (channel.getName().contains("bot") || channel.getName().contains("command"))
                channel.sendMessage("**Failed to send message: No embed links permission**").queue();
            else if (e.toString().toLowerCase().contains("embed")) {
                try {
                    message.delete().queue();
                } catch (Exception exc) {
                    channel.sendMessage("**No manage messages permission.**").queue();
                }
                channel.sendMessage("<@" + author.getId() + ">, **Use this in a bot commands channel instead.**\n*Is this a bot command channel? For me to detect that add in its name the word 'bot' or 'command'(s).*").queue(messageDelete -> messageDelete.delete().queueAfter(10, TimeUnit.SECONDS));
            }
        }
    }
    public void execute(CommandEvent event) {
        Message message = event.getMessage();
        String[] args = event.getArgs().split("\\s");
        if (args[0].equals("help")) {
            event.getChannel().sendMessage("**Shuffle:**\n`>>shuffle <word1> <word2> [other words]`\n<> - Required Argument\n[] - Optional Argument\n\n**Description:** Shuffle a list of words, seperated by space (' ').").queue();
            return;
        }
        List<String> list = Arrays.asList(args);
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
        MessageChannel channel = event.getChannel();
        User author = event.getAuthor();
        shuffleSendCatch(channel, message, author, newContent);
    }
}
