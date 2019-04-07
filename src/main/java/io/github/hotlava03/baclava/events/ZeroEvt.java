package io.github.hotlava03.baclava.events;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class ZeroEvt extends ListenerAdapter{
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        if (event.getAuthor().isBot()) {
            return;
        }
        Message message = event.getMessage();
        String content = message.getContentRaw();
        Guild eclipse = event.getJDA().getGuildById("501535820247728138");
        if((content.equals("zero") ^ (content.equals("0")))){
            MessageChannel channel = event.getChannel();
            try{
                message.addReaction(eclipse.getEmoteById("561489167511715851")).queue();
            }catch(Exception e){
                if(e.toString().toLowerCase().contains("permission"))
                    channel.sendMessage("**I do not have `Add Reactions` permissions.**").queue();
                else
                    channel.sendMessage("**Something went wrong**").queue();
            }
        }
    }
}