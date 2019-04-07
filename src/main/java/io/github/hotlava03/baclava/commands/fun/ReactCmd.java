package io.github.hotlava03.baclava.commands.fun;

import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class ReactCmd extends ListenerAdapter{
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        if (event.getAuthor().isBot()) {
            return;
        }
        Message message = event.getMessage();
        String content = message.getContentRaw();        
        String prefix = ">>";
        if(content.startsWith(prefix + "react")){
            if(content.equals(prefix + "react")){
                try{
                    event.getChannel().sendMessage("Next time you can add emote IDs to a specific channel ID. More info if you run `>>react help`").queue();
                    message.addReaction(event.getJDA().getGuildById("501535820247728138").getEmoteById("501812318158585856")).queue();
                }catch(Exception e){
                    System.out.println("WARNING: SOMETHING WENT WRONG:\t==> "+e);
                    event.getChannel().sendMessage("**Something went wrong**").queue();
                }
                return;
            }else if(content.equals(prefix + "react help") ^ content.equals(prefix + "react")){
                MessageChannel channel = event.getChannel();
                message.delete().queue();
                channel.sendMessage("<@"+ event.getAuthor().getIdLong()+"> \n**Usage:** `>>react` **OR** `>>react <message ID to react> <emote ID>`\n\n\n**Trouble getting emote ID?**\n\n*Insert the emoji in your message.*\n*Send it.*\n*Edit your message with a backslash in the begining.*\n*Copy the numbers inside the full ID.*\n*Insert it in the second parameter of the command.*").queue();
                return;
            }
            User author = message.getAuthor();
            MessageChannel channel = event.getChannel();
            String userName = author.getName();
            try{
                String[] splitContent = content.split("\\s");
                splitContent[0] = "";
                Message messageReact;
                messageReact = event.getChannel().getMessageById(splitContent[1]).complete();
                messageReact.addReaction(event.getGuild().getEmoteById(splitContent[2])).queue();
                message.delete().queue();
            }catch(Exception e){
                channel.sendMessage("**Invalid usage. Make sure the emote you are using is custom.**").queue();
            }
            System.out.println("[ReactCommand] " + userName + " used the react command for this: "+content);
        }
    }
}