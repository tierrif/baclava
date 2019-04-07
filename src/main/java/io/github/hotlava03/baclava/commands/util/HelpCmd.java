package io.github.hotlava03.baclava.commands.util;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.util.Calendar;

public class HelpCmd extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        if (event.getAuthor().isBot()) {
            return;
        }
        Message message = event.getMessage();
        String content = message.getContentRaw();
        String prefix = ">>";
        if(content.equals(prefix + "help")){
            User author = message.getAuthor();
            MessageChannel channel = event.getChannel();
            String userName = author.getName();
            message.addReaction("\u2705").queue();
            System.out.println("[HelpCommand] "+userName+" used the help command.");
            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("**Baclava - Help**", "https://hotlava03.github.io");
            embed.setColor(Color.orange);
            embed.setDescription("**Hello, " + userName + "**\nI am BacLava, and I can do nothing but annoy you.\n **My commands:** \n\n**>>help** - Send this message for help\n**>>ping** - get bot ping towards the API\n**>>info** - Get simple info about the bot\n**>>shuffle** - Give an amount of text (seperated by spaces) and shuffle it randomly\n**>>react** - React to a certain message ID with an emoji ID\n**>>say** - Given text will be sent by the bot, meaning that it will 'say' what you ask it to say.\n**>>suggest <suggestion>** - Sends a suggestion to my owner to bother tf out of him.\n\n\n**Events:**\nSaying you are something (I'm, Im, im, i'm, requires text afterwards) will make me say hi to you\nSay baclava and I'll reply in a 'kind' way\nIf you say zero or 0, I will react with my favourite number, in a cool way.");
            embed.setFooter("Copyright (C) 2019 HotLava03, all rights reserved.",event.getJDA().getUserById("362753440801095681").getAvatarUrl());
            author.openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage(embed.build()).queue());
            Calendar cal = Calendar.getInstance();
            EmbedBuilder embed2 = new EmbedBuilder();
            embed2.setTitle("**DM sent**",null);
            embed2.setFooter(String.valueOf(cal.getTime()),event.getAuthor().getAvatarUrl());
            embed2.setColor(Color.orange);
            embed2.setDescription(userName+", check your DMs for help.");
            channel.sendMessage(embed2.build()).queue();
        }
    }
}
