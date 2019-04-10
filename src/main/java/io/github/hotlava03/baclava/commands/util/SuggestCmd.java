package io.github.hotlava03.baclava.commands.util;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

import java.util.concurrent.TimeUnit;

import static io.github.hotlava03.baclava.Main.ownerID;

public class SuggestCmd extends Command {
    public SuggestCmd(){
        this.name = "suggest";
    }
    public void execute(CommandEvent event){
        Message message = event.getMessage();
        MessageChannel channel = event.getChannel();
        User myself = event.getJDA().getUserById(ownerID);
        String args = event.getArgs();
        try {
            myself.openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage("**[Suggestion]** » From: **" + event.getAuthor().getAsTag() + "**\n```" + args + "```").queue());
            message.delete().queue();
            channel.sendMessage(event.getAuthor().getAsMention() + ", your suggestion has successfully been posted:\n```" + args + "```\nPersonally, I think the idea is real bad, but my owner will still check it out.").queue(ownMessage -> ownMessage.delete().queueAfter(5, TimeUnit.SECONDS));
        }catch(Exception e){
            channel.sendMessage("Error while attempting to process your suggestion. Please try again later.").queue();
        }
    }
}
