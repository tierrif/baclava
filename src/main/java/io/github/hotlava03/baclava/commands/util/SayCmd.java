package io.github.hotlava03.baclava.commands.util;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;

public class SayCmd extends Command {
    public SayCmd(){
        this.name = "say";
    }
    @Override
    public void execute(CommandEvent event) {
        MessageChannel channel = event.getChannel();
        Message message = event.getMessage();
        String[] args = event.getArgs().split("\\s");
        if(args[0].equals("help")) {
            channel.sendMessage("**Usage:** `>>say <text>`\n**Command Description:** \n```Says whatever you type in front of the command.```").queue();
            return;
        }
        try {
            message.delete().queue();
        } catch (Exception e) {
            channel.sendMessage("**I do not have delete messages permissions.**").queue();
        }
        String returnMessage = "";
        for(String arg:args)
            returnMessage += arg + " ";
        channel.sendMessage(returnMessage).queue();
    }
}