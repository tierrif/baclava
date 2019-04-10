package io.github.hotlava03.baclava.eval;

import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.entities.impl.JDAImpl;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Bot
{

    public static Dispatcher dispatcher;
    public static final String INVITE_LINK = "https://discord.gg/0hMr4ce0tIk3pSjp";
    public static JDAImpl jda;
    public static boolean isStealth = false;

    public static OkHttpClient httpClient;

    public static EventListener listener;

    public static final Logger LOG = (Logger) LoggerFactory.getLogger(Bot.class);

    public static final ScheduledExecutorService EXECUTOR = Executors.newSingleThreadScheduledExecutor(MiscUtils.newThreadFactory("main-executor"));

    public static Guild getGuildJda()
    {
        return Bot.jda.getGuildById("125227483518861312");
    }

    public static Role getRoleBots()
    {
        return Bot.getGuildJda().getRoleById("125616720156033024");
    }

    public static Role getRoleStaff()
    {
        return Bot.getGuildJda().getRoleById("169481978268090369");
    }

    public static boolean isAdmin(final User user)
    {
        final Member member = Bot.getGuildJda().getMember(user);
        return member != null && member.getRoles().contains(Bot.getRoleStaff());
    }

    public static Role getRoleHelper()
    {
        return Bot.getGuildJda().getRoleById("183963327033114624");
    }

    public static boolean isHelper(final User user)
    {
        if(isAdmin(user))
            return true;
        final Member member = Bot.getGuildJda().getMember(user);
        return member != null && member.getRoles().contains(Bot.getRoleHelper());
    }

    public static void shutdown()
    {
        shutdown(0);
    }

    public static void shutdown(int code)
    {
        Bot.jda.removeEventListener(Bot.jda.getRegisteredListeners());

        try
        {
            TimeUnit.SECONDS.sleep(2);
        }
        catch (final InterruptedException ignored)
        {}

        Bot.jda.shutdownNow();
        System.exit(code);
    }
}
