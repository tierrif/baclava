package io.github.hotlava03.baclava;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import io.github.hotlava03.baclava.commands.fun.ArtCmd;
import io.github.hotlava03.baclava.commands.fun.ReactCmd;
import io.github.hotlava03.baclava.commands.fun.currency.GiveBLCmd;
import io.github.hotlava03.baclava.commands.games.EightBall;
import io.github.hotlava03.baclava.commands.games.Unscramble;
import io.github.hotlava03.baclava.commands.imageediting.AvatarCmd;
import io.github.hotlava03.baclava.commands.owner.EvalCmd;
import io.github.hotlava03.baclava.commands.owner.FixGameCmd;
import io.github.hotlava03.baclava.commands.owner.Nashorn;
import io.github.hotlava03.baclava.commands.owner.PowerOffCmd;
import io.github.hotlava03.baclava.commands.util.*;
import io.github.hotlava03.baclava.events.ImEvt;
import io.github.hotlava03.baclava.events.ZeroEvt;
import io.github.hotlava03.baclava.misc.ClrScr;
import io.github.hotlava03.baclava.util.Database;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class Main extends ListenerAdapter {
    public static String ownerID;
    public static String version;
    public static JDA jda;
    public static void main(String[] args) throws LoginException, IllegalArgumentException, IOException {
        InputStream file = Main.class.getClassLoader().getResourceAsStream("config.properties");
        Properties properties = new Properties();
        properties.load(file);
        Database db = new Database(properties.getProperty("host"),properties.getProperty("pwd"),properties.getProperty("usr"));
        if(db.getConnection() == null) System.out.println("[Database] An error has occured: Could not find any connections with the database.");
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
                new GetPermsCmd(),
                new EightBall(),
                new AvatarCmd(),
                new Unscramble(waiter),
                new ArtCmd(),
                new GiveBLCmd()
        );
        jda = new JDABuilder(AccountType.BOT)
                .setToken(properties.getProperty("token"))
                .addEventListener(client.build())
                .addEventListener(waiter)
                .build();
        jda.addEventListener(new ImEvt());
        jda.addEventListener(new ZeroEvt());
        ownerID = "362753440801095681";
        version = properties.getProperty("version");
        ClrScr.clrscr();
    }
    //TODO Main todo area:
    /*
    TODO => Add custom Command framework so that users can edit prefix by guild using database.
    TODO => Add fun currency using database with member ID as primary key. [doing]
     */
}
