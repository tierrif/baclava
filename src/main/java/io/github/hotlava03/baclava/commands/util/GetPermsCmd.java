package io.github.hotlava03.baclava.commands.util;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;

import java.awt.*;


public class GetPermsCmd extends Command {
    public GetPermsCmd(){
        this.name = "getperms";
    }
    private EmbedBuilder createPermsEmbed(String send, String arg){
        if(arg.equals("null"))
            arg = "myself";
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("**Permissions for** "+arg+"", null);
        embed.setColor(Color.orange);
        embed.setDescription(send);
        return embed;
    }
    private void createPermsSender(CommandEvent event, String memberType, Object[] perms){
        String send = "";
        for (Object pos : perms) {
            send += pos + "\n";
        }
        try {
            event.getChannel().sendMessage(createPermsEmbed(send, memberType).build()).complete();
        }catch(Exception e){
            event.getChannel().sendMessage("**Warning:** *No embed links permission, at least for this channel.*\n"+send).complete();
        }
    }
    public void execute(CommandEvent event){
        String[] args = event.getArgs().split("\\s");
        if(args[0].equals("")) {
            Object[] perms = event.getSelfMember().getPermissions().toArray();
            createPermsSender(event, "null", perms);
            return;
        }else if(args[0].contains("help"))
            event.getChannel().sendMessage("**Usage:** *>>getperms [userAsMention]").queue();
        //if the command has arguments
        if(args.length > 1){
            event.getChannel().sendMessage("**Excessive arguments. Number of arguments for this command: `1`").queue();
            return;
        }
        //if usage is correct, with arguments
        String memberID = args[0].replace("<@","");
        if(memberID.contains("!"))
            memberID = memberID.replace("!","");
        memberID = memberID.replace(">","");
        Member member = event.getGuild().getMemberById(memberID);
        Object[] perms = member.getPermissions().toArray();
        createPermsSender(event, member.getEffectiveName(), perms);
    }
}
