package io.github.hotlava03.baclava.commands.owner;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import io.github.hotlava03.baclava.Main;
import net.dv8tion.jda.core.entities.Game;

import static io.github.hotlava03.baclava.Main.jda;

public class FixGameCmd extends Command {
    public FixGameCmd(){
        this.name = "fixgame";
    }
    public void execute(CommandEvent event){
        if(!event.getAuthor().getId().equals(Main.ownerID)){
            event.getChannel().sendMessage("thanks for trying to be nice, but no").queue();
            return;
        }
        jda.getPresence().setGame(Game.playing(">>help for bot help"));
        event.getChannel().sendMessage("\u2705 | Successfully fixed status.").queue();
    }
}
