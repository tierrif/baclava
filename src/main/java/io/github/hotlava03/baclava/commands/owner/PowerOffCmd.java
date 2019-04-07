package io.github.hotlava03.baclava.commands.owner;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class PowerOffCmd extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String prefix = ">>";
        if(event.getAuthor().isBot())
            return;
        if(!(event.getMessage().getContentRaw().startsWith(prefix+"poweroff")))
            return;
        if(!(event.getAuthor().getId().equals("362753440801095681"))) {
            event.getChannel().sendMessage("\u26d4 | Sorry, you do not have permission to use `poweroff`.").queue();
            return;
        }
        event.getChannel().sendMessage("**Shutting down...**").queue();
        System.exit(1);
    }
}

