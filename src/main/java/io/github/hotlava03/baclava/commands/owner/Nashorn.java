package io.github.hotlava03.baclava.commands.owner;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Nashorn extends Command {
    private ScriptEngine engine;
    public Nashorn()
    {
        this.name = "nashorn";
        engine = new ScriptEngineManager().getEngineByName("nashorn");
        try
        {
            engine.eval("var imports = new JavaImporter(" +
                    "java.io," +
                    "java.lang," +
                    "java.eval," +
                    "Packages.net.dv8tion.jda.core," +
                    "Packages.net.dv8tion.jda.core.entities," +
                    "Packages.net.dv8tion.jda.core.entities.impl," +
                    "Packages.net.dv8tion.jda.core.managers," +
                    "Packages.net.dv8tion.jda.core.managers.impl," +
                    "Packages.com.jagrosh.jdautilities.command.Command," +
                    "Packages.com.jagrosh.jdautilities.command.CommandEvent," +
                    "Packages.net.dv8tion.jda.core.utils);");
        }
        catch (ScriptException e)
        {
            e.printStackTrace();
        }
    }
    public void execute(CommandEvent event) {
        if (!event.getAuthor().getId().equalsIgnoreCase("362753440801095681"))
        {
            event.getChannel().sendMessage("no ty").queue();
            return;
        }
        try
        {
            engine.put("event", event);
            engine.put("message", event.getMessage());
            engine.put("channel", event.getChannel());
            engine.put("args", event.getArgs());
            engine.put("api", event.getJDA());
            try {
                engine.put("guild", event.getGuild());
                engine.put("member", event.getMember());
            }catch(Exception exception){
                event.getChannel().sendMessage("**Error while putting event.getGuild() and event.getMember() in engine.**").queue();
            }
            Object out = engine.eval(
                    "(function() {" +
                            "with (imports) {" +
                            event.getArgs() +
                            "}" +
                            "})();");
            if(out == null) {
                event.getMessage().addReaction("\u2705").queue();
            }else{
                event.reply("```"+out.toString()+"```");
            }
        }
        catch (Exception e1)
        {
            event.getMessage().addReaction("\u274c").queue();
        }
    }
}
