package io.github.hotlava03.baclava.commands.util;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class PingCmd extends ListenerAdapter{
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        if (event.getAuthor().isBot()) {
            return;
        }
        Message message = event.getMessage();
        String content = message.getContentRaw(); 
        String prefix = ">>";
        if(content.startsWith(prefix + "ping")){
            //int ping = (int) event.getJDA().getPing();
            long time = System.currentTimeMillis();
            event.getChannel().sendMessage("*Pinging, please wait...*").queue( (m) -> m.editMessageFormat("**My ping:** %d**ms**", (System.currentTimeMillis() - time) / 2).queue());
        }
    }
}
