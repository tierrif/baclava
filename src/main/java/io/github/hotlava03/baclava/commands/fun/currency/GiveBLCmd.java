package io.github.hotlava03.baclava.commands.fun.currency;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import io.github.hotlava03.baclava.Main;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class GiveBLCmd extends Command {
    public GiveBLCmd(){
        this.name = "baclava";
        this.aliases = new String[]{"bl","givebaclava","gb","gbl","gimme"};
    }
    public void execute(CommandEvent event){
        if(event.getArgs().split(" ").length > 1){
            event.reply("What exactly are you trying to do? `>>baclava` is the command. No args >_>");
            return;
        }
        InputStream file = Main.class.getClassLoader().getResourceAsStream("config.properties");
        Properties properties = new Properties();
        try{
            properties.load(file);
        }catch(IOException e){
            event.reply("Something went wrong while getting `config.properties`. Please use >>suggest to report this.");
            return;
        }
        Connection connection;
        try{
            connection = DriverManager.getConnection(properties.getProperty("host"),properties.getProperty("usr"),properties.getProperty("pwd"));
        }catch(SQLException e){
            event.reply("Failed to connect to the MySQL database: `bldb01`");
            return;
        }
        Statement statement;
        ResultSet result;
        long id;
        try {
            /*if(connection.prepareStatement("SELECT * FROM baclava.baclavaAmount WHERE `userId` = "+event.getAuthor().getId()).executeQuery() == null) {
                statement = connection.prepareStatement("INSERT INTO baclava.baclavaAmount(userId,amount) VALUES (" + event.getAuthor().getId() + ",1)");
                result = statement.executeQuery();
                if(result.getLong("userId") == 1)
                    event.reply(event.getAuthor().getName() + " has received **1 baclava** ");
                else
                    event.reply("fail");
                return;
            }*/
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            if(statement.executeQuery("SELECT * FROM baclava.baclavaAmount WHERE `userId` = "+event.getAuthor().getId()).wasNull()){
                result = statement.executeQuery("INSERT INTO baclava.baclavaAmount(userId,amount) VALUES (" + event.getAuthor().getId() + ",1)");
                if(result.getLong("userId") == 1)
                    event.reply(event.getAuthor().getName() + " has received **1 baclava** ");
                else
                    event.reply("fail");
                return;
            }
        }catch(SQLException e){
            event.reply("Failed to get data from the MySQL database. `bldb02`");
            System.out.println(e);
            return;
        }
        event.reply("Finished code");
    }
}
