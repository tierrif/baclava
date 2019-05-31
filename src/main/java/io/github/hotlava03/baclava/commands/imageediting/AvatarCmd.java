package io.github.hotlava03.baclava.commands.imageediting;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.User;

public class AvatarCmd extends Command {
    public AvatarCmd(){
        this.name = "avatar";
        this.aliases = new String[] {"av","a","profile","pic","picture"};
    }
    private void createEmbed(CommandEvent event, User arg){
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle(arg.getName()+"'s avatar [Click me]",arg.getAvatarUrl()+"?size=2048");
        embed.setImage(arg.getAvatarUrl()+"?size=2048");
        embed.setFooter(event.getAuthor().getName()+"#"+event.getAuthor().getDiscriminator(),arg.getAvatarUrl());
        event.reply(embed.build());
    }
    public void execute(CommandEvent event){
        if(event.getArgs().split("\\s")[0].equals("")){
            createEmbed(event,event.getAuthor());
            return;
        }
        createEmbed(event,event.getMessage().getMentionedMembers().get(0).getUser());
    }
}
