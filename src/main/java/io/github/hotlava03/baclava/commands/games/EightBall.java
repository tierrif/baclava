package io.github.hotlava03.baclava.commands.games;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EightBall extends Command {
    public EightBall(){
        this.name = "8ball";
    }
    private EmbedBuilder createEmbed(CommandEvent event, Object finalEightBall, String[] args){
        StringBuilder sb = new StringBuilder();
        for(String arg : args){
            sb.append(arg);
            sb.append(" ");
        }
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("**The mighty 8-ball says...**",null);
        embed.setDescription(finalEightBall.toString());
        embed.setFooter("Requested: " + sb,event.getAuthor().getAvatarUrl());
        embed.setColor(Color.orange);
        return embed;
    }
    @Override
    public void execute(CommandEvent event){
        if(event.getArgs().split("\\s")[0].equals("")){
            event.getChannel().sendMessage("If you want the 8 ball to respond, you must ask it something.").queue();
            return;
        }
        String[] eightBall = {"maybe", "yes", "no", "probably not", "no idea", "try again", "obviously", "obviously not"};
        List<String> list = Arrays.asList(eightBall);
        Collections.shuffle(list);
        Object[] finalEightBall = list.toArray();
        try {
            event.getChannel().sendMessage(createEmbed(event, finalEightBall[0], event.getArgs().split("\\s")).build()).complete();
        }catch(Exception e){
            event.getChannel().sendMessage("Failed to send embed message: Missing Permitions (Embed Links)").queue();
        }
    }
}
