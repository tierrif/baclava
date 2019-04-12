package io.github.hotlava03.baclava.commands.owner;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class PowerOffCmd extends Command {
    public PowerOffCmd(){
        this.name = "poweroff";
    }
    public void execute(CommandEvent event){
        if(!(event.getAuthor().getId().equals("362753440801095681"))) {
            event.getChannel().sendMessage("how dare you").queue();
            return;
        }
        event.getChannel().sendMessage("urgh why do you have to do this... *ok bye*").queue();
        System.exit(1);
    }
}
