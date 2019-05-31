package io.github.hotlava03.baclava.commands.util;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;

import java.awt.*;
import java.util.Calendar;

public class HelpCmd extends Command {
    public HelpCmd() {
        this.name = "help";
    }
    @Override
    protected void execute(CommandEvent event){
            event.getMessage().addReaction("\u2705").queue();
            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("**Baclava - Help**", "https://hotlava03.github.io");
            embed.setColor(Color.orange);
            embed.setDescription("**Hello, " + event.getAuthor().getName() + "**\nI am BacLava, and I can do nothing but annoy you.\n **My commands:** \n\n**>>help** - Send this message for help\n**>>ping** - get bot ping towards the API\n**>>info** - Get simple info about the bot\n**>>shuffle** - Give an amount of text (seperated by spaces) and shuffle it randomly\n**>>react** - React to a certain message ID with an emoji ID\n**>>uptime:** Sends bot uptime.\n**>>getperms** - Get perms from the bot (no args) or with a mention in first arg, perms from the mentioned user will be given.\n**>>8ball** - Answers a yes or no question, with some insecurities. I got this ball from an old store though, don't take it seriously.\n**>>scrabble** - The best game I can play with you. Unscramble a word I give and you win nothing!\n**>>say** - Given text will be sent by the bot, meaning that it will 'say' what you ask it to say.\n**>>suggest <suggestion>** - Sends a suggestion to my owner to bother tf out of him.\n\n\n**Events:**\nSaying you are something (I'm, Im, im, i'm, requires text afterwards) will make me say hi to you\nSay baclava and I'll reply in a 'kind' way\nIf you say zero or 0, I will react with my favourite number, in a cool way.");
            embed.setFooter("Copyright (C) 2019 HotLava03, all rights reserved.",event.getJDA().getUserById("362753440801095681").getAvatarUrl());
            event.getAuthor().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage(embed.build()).queue());
            Calendar cal = Calendar.getInstance();
            EmbedBuilder embed2 = new EmbedBuilder();
            embed2.setTitle("**DM sent**",null);
            embed2.setFooter(String.valueOf(cal.getTime()),event.getAuthor().getAvatarUrl());
            embed2.setColor(Color.orange);
            embed2.setDescription(event.getAuthor().getName()+", check your DMs for help.");
            event.getChannel().sendMessage(embed2.build()).queue();
    }
}
