package io.github.hotlava03.baclava.commands.util;

import java.awt.Color;
import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class InfoCmd extends ListenerAdapter{
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        if (event.getAuthor().isBot()) {
            return;
        }
        Message message = event.getMessage();
        String content = message.getContentRaw();
        String prefix = ">>";
        String channelName = event.getChannel().getName();
        if(content.equals(prefix + "info")){
            MessageChannel channel = event.getChannel();
            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("**Baclava - Info**", "https://hotlava03.github.io");
            embed.setColor(Color.orange);
            embed.setDescription("**Version**  1.0.1\n**Library**  JDA (Java Discord API)\n**Owner** Lava#0654");
            embed.setFooter("Copyright (C) 2019 HotLava03, all rights reserved.",event.getJDA().getUserById("362753440801095681").getAvatarUrl());
            try{
                channel.sendMessage(embed.build()).queue();
            }catch(Exception e){
                if(channelName.contains("bot") || channelName.contains("command"))
                    channel.sendMessage("**Failed to send message: No embed links permission**").queue();
                else if(e.toString().toLowerCase().contains("embed")) {
                    try{
                        message.delete().queue();
                    }catch(Exception exc){
                        channel.sendMessage("**No manage messages permission.**").queue();
                    }
                    channel.sendMessage("<@"+event.getAuthor().getId()+">, **Use this in a bot commands channel instead.**\n*Is this a bot command channel? For me to detect that add in its name the word 'bot' or 'command'(s).*").queue(messageDelete -> messageDelete.delete().queueAfter(10, TimeUnit.SECONDS));
                }
            }
            
        }
    }
}
