package io.github.hotlava03.baclava;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import io.github.hotlava03.baclava.commands.fun.ReactCmd;
import io.github.hotlava03.baclava.commands.owner.EvalCmd;
import io.github.hotlava03.baclava.commands.owner.FixGameCmd;
import io.github.hotlava03.baclava.commands.owner.Nashorn;
import io.github.hotlava03.baclava.commands.owner.PowerOffCmd;
import io.github.hotlava03.baclava.commands.util.*;
import io.github.hotlava03.baclava.events.ImEvt;
import io.github.hotlava03.baclava.events.ZeroEvt;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

import static io.github.hotlava03.baclava.misc.ClrScr.clrscr;

public class Main extends ListenerAdapter {
    public static String ownerID;
    public static JDA jda;
    public static void main(String[] args) throws LoginException, IllegalArgumentException {
        CommandClientBuilder client = new CommandClientBuilder();
        EventWaiter waiter = new EventWaiter();
        client.setPrefix(">>");
        client.setOwnerId("362753440801095681");
        client.useHelpBuilder(false);
        client.setGame(Game.playing(">>help for help"));
        client.addCommands(
                new HelpCmd(),
                new PingCmd(),
                new InfoCmd(),
                new ShuffleCmd(),
                new FixGameCmd(),
                new SayCmd(),
                new SuggestCmd(),
                new Nashorn(),
                new EvalCmd(),
                new ReactCmd(),
                new PowerOffCmd(),
                new UptimeCmd(),
                new GetPermsCmd()
        );
        jda = new JDABuilder(AccountType.BOT)
                .setToken("tokenHere")
                .addEventListener(client.build())
                .build();
        jda.addEventListener(new ImEvt());
        jda.addEventListener(new ZeroEvt());
        ownerID = "362753440801095681";
        clrscr();
    }
}
