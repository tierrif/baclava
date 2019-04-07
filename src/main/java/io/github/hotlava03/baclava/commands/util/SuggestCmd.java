package io.github.hotlava03.baclava.commands.util;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.concurrent.TimeUnit;

public class SuggestCmd extends ListenerAdapter{
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        if (event.getAuthor().isBot()) {
            return;
        }
        Message message = event.getMessage();
        String content = message.getContentRaw();
        String prefix = ">>";
        if(!content.startsWith(prefix + "suggest"))
            return;
        MessageChannel channel = event.getChannel();
        content = content.replace(">>suggest ", "");
        final String finalContent = content;
        User myself = event.getJDA().getUserById("362753440801095681");
        try {
            myself.openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage("**[Suggestion]** » From: **" + event.getAuthor().getAsTag() + "**\n```" + finalContent + "```").queue());
            message.delete().queue();
            channel.sendMessage(event.getAuthor().getAsMention() + ", your suggestion has successfully been posted:\n```" + content + "```\nPersonally, I think the idea is real bad, but my owner will still check it out.").queue(ownMessage -> ownMessage.delete().queueAfter(5, TimeUnit.SECONDS));
        }catch(Exception e){
            channel.sendMessage("Error while attempting to process your suggestion. Please try again later.").queue();
        }
    }
}
