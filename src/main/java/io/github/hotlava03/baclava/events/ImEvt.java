package io.github.hotlava03.baclava.events;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.concurrent.ThreadLocalRandom;

public class ImEvt extends ListenerAdapter{
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        if (event.getAuthor().isBot()) {
            return;
        }        
        Message message = event.getMessage();
        String content = message.getContentRaw();
        if(content.toLowerCase().equals("baclava") || content.contains("<@554401969234771969>")){
            if(ThreadLocalRandom.current().nextInt(0, 10) == 0)
                event.getChannel().sendMessage("what do you want").queue();
            return;
        }else if(content.contains("<@!362753440801095681>")) {
            if(ThreadLocalRandom.current().nextInt(0, 10) == 0)
                event.getChannel().sendMessage("no tagging my owner thanks").queue();
        }
        if((content.toLowerCase().contains("suck"))){
            if(ThreadLocalRandom.current().nextInt(0, 10) == 0)
                event.getChannel().sendMessage("ok").queue();
        }
        
    }
}
