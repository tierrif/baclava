package io.github.hotlava03.baclava.commands.fun;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.exceptions.ErrorResponseException;

import java.awt.*;
import java.util.Calendar;
import java.util.List;


public class ReactCmd extends Command {
    public ReactCmd(){
        this.name = "react";
    }
    private EmbedBuilder helpEmbed(CommandEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("**Help - React**");
        embed.setDescription("**Command: ** `react`\n**Description:** *React to a specified message ID with a guild emote, specified by name.* **Notice:** The first found emote, in case of duplicate, will be the used one.\n**Syntax:** `>>react <messageID> <emoteName>`\n\n**If the syntax is not correct, no reactions will be done.**");
        embed.setColor(Color.orange);
        Calendar cal = Calendar.getInstance();
        embed.setFooter(String.valueOf(cal.getTime()),event.getAuthor().getAvatarUrl());
        return embed;
    }
    @Override
    public void execute(CommandEvent event) {
        String[] args = event.getArgs().split("\\s");
        //TODO get this working. REMINDER: FEATURE COMING SOON
        /* if(args[0].equals("disable")) {
            try {
                CommandDisabler.disableCmd("reactIsDisabled");
                event.getChannel().sendMessage("Disabled `react`").queue();
                return;
            }catch(IOException e){
                event.getChannel().sendMessage("**Could not disable command: `File not found.`").queue();
            }
        }else if(args[0].equals("enable")) {
            try {
                CommandDisabler.enableCmd("reactIsDisabled");
                event.getChannel().sendMessage("Enabled `react`").queue();
                return;
            }catch(IOException e){
                event.getChannel().sendMessage("**Could not enable command: `File not found.`").queue();
            }
        }
        try{
            if(!CommandDisabler.isEnabled("reactIsDisabled"))
                return;
        }catch(IOException e){
            event.getChannel().sendMessage("**Could not verify if command is disabled or enabled: `File not found.`").queue();
        }*/
        MessageChannel channel = event.getChannel();
        if(args[0].equals("help") || args[0].equals("")) {
            channel.sendMessage(helpEmbed(event).build()).queue();
            return;
        }
        try{
            Message messageReact;
            messageReact = event.getChannel().getMessageById(args[0]).complete();
            List<Emote> list = event.getGuild().getEmotesByName(args[1],true);
            try{
                messageReact.addReaction(list.get(0)).queue();
            }catch(ErrorResponseException e){
                channel.sendMessage("**Failed to find emote in this guild. Make sure the name is correct and is part of this server.**").queue();
            }
        }catch(Exception e){
            channel.sendMessage("**Failed to add emote: Make sure the emote you are using is custom and/or is valid and part of this guild.**").queue();
        }
    }
}
