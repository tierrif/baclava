package io.github.hotlava03.baclava.events;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class ImEvt extends ListenerAdapter{
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        if (event.getAuthor().isBot()) {
            return;
        }        
        Message message = event.getMessage();
        String content = message.getContentRaw();
        if(content.toLowerCase().equals("baclava") || content.contains("<@554401969234771969>")){
             User author = message.getAuthor();
            MessageChannel channel = event.getChannel();
            String userName = author.getName();
            System.out.println("[ImEvent] " + userName + " said 'baclava' in a sentence.");
            channel.sendMessage("what do you want").queue();
            return;
        }else if(content.contains("<@!362753440801095681>"))
            event.getChannel().sendMessage("no tagging my owner thanks").queue();
        /*if((content.contains("im ")) ^ (content.contains("i'm ") ^ (content.contains("Im ") ^ (content.contains("I'm ") )))){
            if(event.getGuild().getId().equals("501535820247728138"))
                return;
             User author = message.getAuthor();
            MessageChannel channel = event.getChannel();
            String userName = author.getName();
            System.out.println("[ImEvent] " + userName + " said 'im' in a sentence.");
            String[] splitContent = content.split("\\s");
            splitContent[0] = "";
            StringBuilder builder = new StringBuilder();
            for(String s : splitContent) {
                builder.append(s);
            }
            content = builder.toString();           
            channel.sendMessage("Hi " + content + ", I'm BacLava").queue();
        }*/
        if((content.toLowerCase().contains("suck"))){
            MessageChannel channel = event.getChannel();
            channel.sendMessage("ok").queue();
        }
        
    }
}