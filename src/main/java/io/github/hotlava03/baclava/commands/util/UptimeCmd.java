package io.github.hotlava03.baclava.commands.util;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class UptimeCmd extends Command {
    public UptimeCmd(){
        this.name = "uptime";
    }
    public static String getUptime(){
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        long uptime = runtime.getUptime();
        long seconds = uptime / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        String time;
        if(days > 0)
            time = ("The bot's uptime is: "+days + " days, " + hours % 24 + " hours, " + minutes % 60 + " minutes and " + seconds % 60 + " seconds.");
        else if((hours % 24) > 0)
            time = ("The bot's uptime is: "+ hours % 24 + " hours, " + minutes % 60 + " minutes and " + seconds % 60 + " seconds.");
        else if((minutes % 60) > 0)
            time = ("The bot's uptime is: "+ minutes % 60 + " minutes and " + seconds % 60 + " seconds.");
        else
            time = "The bot was just powered on, and its uptime is: "+seconds%60+" seconds.";
        return time;
    }
    public void execute(CommandEvent event){
        event.getChannel().sendMessage(getUptime()).queue();
    }
}
