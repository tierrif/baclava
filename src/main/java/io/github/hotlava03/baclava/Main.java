package io.github.hotlava03.baclava;

import io.github.hotlava03.baclava.commands.fun.ReactCmd;
import io.github.hotlava03.baclava.commands.owner.Eval;
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

public class Main extends ListenerAdapter {
    public static JDA jda;
    public static void main(String[] args) throws LoginException {
        jda = new JDABuilder(AccountType.BOT)
                .setToken("tokenHere")
                .build();
        jda.addEventListener(new HelpCmd());
        jda.addEventListener(new ImEvt());
        jda.addEventListener(new PingCmd());
        jda.addEventListener(new ZeroEvt());
        jda.addEventListener(new InfoCmd());
        jda.addEventListener(new ReactCmd());
        jda.addEventListener(new RandomCmd());
        jda.addEventListener(new PowerOffCmd());
        jda.addEventListener(new SuggestCmd());
        jda.addEventListener(new Eval());
        jda.getPresence().setGame(Game.playing(">>help for help"));
    }
}
