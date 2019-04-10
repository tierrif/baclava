package io.github.hotlava03.baclava.commands.util;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class PingCmd extends Command {
    public PingCmd(){
        this.name = "ping";
    }
    @Override
    protected void execute(CommandEvent event){
        long time = System.currentTimeMillis();
        event.getChannel().sendMessage("*Pinging, please wait...*").queue(m -> m.editMessageFormat("**My ping:** %d**ms**", (System.currentTimeMillis() - time) / 2).queue());
    }
}
