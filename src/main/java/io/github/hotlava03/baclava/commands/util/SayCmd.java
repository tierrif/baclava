package io.github.hotlava03.baclava.commands.util;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class SayCmd extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }
        Message message = event.getMessage();
        String content = message.getContentRaw();
        String prefix = ">>";
        if (content.equals(prefix + "say") || content.equals(prefix + "say help")) {
            MessageChannel channel = event.getChannel();
            channel.sendMessage("**Usage:** `" + prefix + "say <text>`\n**Command Description:** \n```Says whatever you type in front of the command.```").queue();
        } else if (content.startsWith(prefix + "say ")) {
            MessageChannel channel = event.getChannel();
            try {
                message.delete().queue();
            } catch (Exception e) {
                channel.sendMessage("**I do not have delete messages permissions.**").queue();
            }
            char[] splitContent = content.toCharArray();
            for(int i = 0; i <= 5; i++)
                splitContent[i] = ' ';
            content = String.valueOf(splitContent);
            content = content.replace("      ","");
            channel.sendMessage(content).queue();
            System.out.println("[SayCommand] " + event.getAuthor().getAsTag() + " used the say command for this: " + content);
        }
    }
}