package io.github.hotlava03.baclava.eval;


import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;
import org.slf4j.Logger;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class MiscUtils
{
    public static ThreadFactory newThreadFactory(String threadName)
    {
        return newThreadFactory(threadName, Bot.LOG);
    }

    public static ThreadFactory newThreadFactory(String threadName, boolean isDaemon)
    {
        return newThreadFactory(threadName, Bot.LOG, isDaemon);
    }

    public static ThreadFactory newThreadFactory(String threadName, Logger logger)
    {
        return newThreadFactory(threadName, logger, true);
    }

    public static ThreadFactory newThreadFactory(String threadName, Logger logger, boolean isdaemon)
    {
        return (r) ->
        {
            Thread t = new Thread(r, threadName);
            t.setDaemon(isdaemon);
            t.setUncaughtExceptionHandler((final Thread thread, final Throwable throwable) ->
                    logger.error("There was a uncaught exception in the {} threadpool", thread.getName(), throwable));
            return t;
        };
    }


    public static void announce(TextChannel channel, Role role, Message message, boolean slowmode)
    {
        if (slowmode)
        {
            channel.getManager().setSlowmode(30).queue(v -> channel.getManager().setSlowmode(0).queueAfter(2, TimeUnit.MINUTES));
        }

        role.getManager().setMentionable(true).queue(v ->
                channel.sendMessage(message).queue(v2 ->
                        role.getManager().setMentionable(false).queue()
                )
        );
    }
}