package io.github.hotlava03.baclava.commands.owner;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Eval extends ListenerAdapter {
    private ScriptEngine engine;
    public Eval() //ignore the hint, must leave it public
    {
        engine = new ScriptEngineManager().getEngineByName("nashorn");
        try
        {
            engine.eval("var imports = new JavaImporter(" +
                    "java.io," +
                    "java.lang," +
                    "java.util," +
                    "Packages.net.dv8tion.jda.core," +
                    "Packages.net.dv8tion.jda.core.entities," +
                    "Packages.net.dv8tion.jda.core.entities.impl," +
                    "Packages.net.dv8tion.jda.core.managers," +
                    "Packages.net.dv8tion.jda.core.managers.impl," +
                    "Packages.net.dv8tion.jda.core.utils);");
        }
        catch (ScriptException e)
        {
            e.printStackTrace();
        }
    }
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if(!(event.getMessage().getContentRaw().startsWith(">>eval ")))
            return;
        String messageContent = event.getMessage().getContentDisplay();
        if (!event.getAuthor().getId().equalsIgnoreCase("362753440801095681"))
        {
            event.getChannel().sendMessage("\u26d4 | Sorry, you do not have permission to use `eval`.").queue();
            return;
        }
        messageContent = messageContent.replace(">>eval ", "");
        try
        {
            engine.put("event", event);
            engine.put("message", event.getMessage());
            engine.put("channel", event.getChannel());
            engine.put("args", messageContent);
            engine.put("api", event.getJDA());
            try {
                engine.put("guild", event.getGuild());
                engine.put("member", event.getMember());
            }catch(Exception exception){
                event.getChannel().sendMessage("Fatal error: at\ntry{\n\tengine.put(\"guild\", event.getGuild());\n\tengine.put(\"member\", event.getMember());\n}").queue();
            }

            Object out = engine.eval(
                    "(function() {" +
                            "with (imports) {" +
                            messageContent +
                            "}" +
                            "})();");
            //event.reply( out == null ? "Executed without error." : out.toString());
            event.getMessage().addReaction("\u2705").queue();
        }
        catch (Exception e1)
        {
            //event.getChannel().sendMessage(e1.getMessage()).queue();
            event.getMessage().addReaction("\u274c").queue();
        }
    }
}
