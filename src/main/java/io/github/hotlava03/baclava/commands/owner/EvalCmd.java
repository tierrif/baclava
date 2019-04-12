package io.github.hotlava03.baclava.commands.owner;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import io.github.hotlava03.baclava.eval.Engine;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.requests.RestAction;
import org.apache.commons.lang3.tuple.Triple;

import java.util.*;

import static io.github.hotlava03.baclava.Main.ownerID;

public class EvalCmd extends Command {
    public EvalCmd(){
        this.name = "eval";
    }
    public void execute(CommandEvent event){
        if(!event.getAuthor().getId().equals(ownerID)) {
            event.getChannel().sendMessage("no eval for you").queue();
            return;
        }
        // Execute code
        final Map<String, Object> shortcuts = new HashMap<>();
        Message message = event.getMessage();
        MessageChannel channel = event.getChannel();
        User author = event.getAuthor();
        shortcuts.put("api", message.getJDA());
        shortcuts.put("jda", message.getJDA());
        shortcuts.put("event", event);

        shortcuts.put("channel", channel);
        shortcuts.put("server", event.getGuild());
        shortcuts.put("guild", event.getGuild());

        shortcuts.put("message", message);
        shortcuts.put("msg", message);
        shortcuts.put("me", author);
        shortcuts.put("author", author);
        shortcuts.put("bot", message.getJDA().getSelfUser());

        final Triple<Object, String, String> result = Engine.GROOVY.eval(shortcuts, Collections.emptyList(), Engine.DEFAULT_IMPORTS, 1, event.getArgs());

        if (result.getLeft() instanceof RestAction<?>)
            ((RestAction<?>) result.getLeft()).queue();
        /*else if (result.getLeft() != null)
            builder.appendCodeBlock(result.getLeft().toString(), "");
        if (!result.getMiddle().isEmpty())
            builder.append("\n").appendCodeBlock(result.getMiddle(), "");
        if (!result.getRight().isEmpty())
            builder.append("\n").appendCodeBlock(result.getRight(), "");*/

        String out = result.toString();
        out = out.replace(",,)","");
        out = out.replace("(","");
        if(out.contains("ScriptException")) {
            try {
                message.addReaction("\u274c").complete();
            }catch(Exception e){
                channel.sendMessage("\u274c - (Eval) No reaction perms, but the eval was executed **unsuccessfully**.").queue();
            }
        }else if(!(out.equals("null")))
            channel.sendMessage("```"+out+"```").queue();
        else{
            try{
                message.addReaction("\u2705").complete();
            }catch(Exception e){
                channel.sendMessage("\u2705 - (Eval) No reaction perms, but the eval was executed **successfully**.").queue();
            }
        }

        /*if (builder.isEmpty())
            event.reply("Add some code else I can't really do anything you know, like... what you used to get me working?");
        else
            for (final Message m : builder.buildAll(MessageBuilder.SplitPolicy.NEWLINE, MessageBuilder.SplitPolicy.SPACE, MessageBuilder.SplitPolicy.ANYWHERE))
                event.reply(""+m);*/
    }
}
