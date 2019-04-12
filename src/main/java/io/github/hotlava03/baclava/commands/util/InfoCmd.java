package io.github.hotlava03.baclava.commands.util;

import java.awt.Color;
import java.util.concurrent.TimeUnit;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;


public class InfoCmd extends Command {
    public InfoCmd(){
        this.name = "info";
    }
    private EmbedBuilder createInfoEmbed(User myself){
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("**Baclava - Info**", "https://hotlava03.github.io");
        embed.setColor(Color.orange);
        embed.setDescription("**Version:**  (Unreleased)\n**Library:**  JDA (Java Discord API)\n**Uptime: **"+ UptimeCmd.getUptime()+"\n**Owner:** Lava#0654");
        embed.setFooter("Copyright (C) 2019 HotLava03, all rights reserved.",myself.getAvatarUrl());
        return embed;
    }
    public void execute(CommandEvent event){
        Message message = event.getMessage();
        String channelName = event.getChannel().getName();
        MessageChannel channel = event.getChannel();
        User myself = event.getJDA().getUserById("362753440801095681");
        try{
            channel.sendMessage(createInfoEmbed(myself).build()).queue();
        }catch(Exception e) {
            if (channelName.contains("bot") || channelName.contains("command"))
                channel.sendMessage("**Failed to send message: No embed links permission**").queue();
            else if (e.toString().toLowerCase().contains("embed")) {
                try {
                    message.delete().queue();
                } catch (Exception exc) {
                    channel.sendMessage("**No manage messages permission.**").queue();
                }
                channel.sendMessage("<@" + event.getAuthor().getId() + ">, **Use this in a bot commands channel instead.**\n*Is this a bot command channel? For me to detect that add in its name the word 'bot' or 'command'(s).*").queue(messageDelete -> messageDelete.delete().queueAfter(10, TimeUnit.SECONDS));
            }
        }
    }
}
