package io.github.hotlava03.baclava.commands.games;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Unscramble extends Command {
    private final EventWaiter waiter;

    public Unscramble(EventWaiter waiter) {
        this.waiter = waiter;
        this.name = "scrabble";
    }

    public void execute(CommandEvent event) {
        String[] developer = new String[]{"develop", "string", "integer", "float", "method", "java", "python", "javascript", "class", "function"};
        String[] animal = new String[]{"giraffe", "cat", "dog", "lion", "elefant", "penguin", "rabbit", "pig", "cow", "fish", "cockroach"};
        String[] school = new String[]{"pencil", "blackboard", "teacher", "student", "book", "table", "classroom", "grades"};
        event.reply("*Warning: this command is in progress. Issues may occur. Please follow all instructions wisely, and use words as all arguments were case sensitive. Report all bugs through >>suggest <bug>.*\n\nChoose a word theme: `developer`, `animal`, `school`");
        List<String> developerList = Arrays.asList(developer);
        List<String> animalList = Arrays.asList(animal);
        List<String> schoolList = Arrays.asList(school);
        Collections.shuffle(developerList);
        Collections.shuffle(animalList);
        Collections.shuffle(schoolList);
        String developerChosenWord = developerList.toArray()[0].toString();
        String animalChosenWord = animalList.toArray()[0].toString();
        String schoolChosenWord = schoolList.toArray()[0].toString();
        char[] developerUnscramble = developerChosenWord.toCharArray();
        char[] animalUnscramble = animalChosenWord.toCharArray();
        char[] schoolUnscramble = schoolChosenWord.toCharArray();
        List<Character> developerUnscrambleList = new ArrayList<>();
        List<Character> animalUnscrambleList = new ArrayList<>();
        List<Character> schoolUnscrambleList = new ArrayList<>();
        for(char pos : developerUnscramble)
            developerUnscrambleList.add(pos);
        for(char pos : animalUnscramble)
            animalUnscrambleList.add(pos);
        for(char pos : schoolUnscramble)
            schoolUnscrambleList.add(pos);
        Collections.shuffle(developerUnscrambleList);
        Collections.shuffle(animalUnscrambleList);
        Collections.shuffle(schoolUnscrambleList);
        Object[] unscrambledDeveloper = developerUnscrambleList.toArray();
        Object[] unscrambledAnimal = animalUnscrambleList.toArray();
        Object[] unscrambledSchool = schoolUnscrambleList.toArray();
        StringBuilder sbDev = new StringBuilder();
        StringBuilder sbAni = new StringBuilder();
        StringBuilder sbSch = new StringBuilder();
        for(Object o : unscrambledDeveloper)
            sbDev.append(o);
        for(Object o : unscrambledAnimal)
            sbAni.append(o);
        for(Object o : unscrambledSchool)
            sbSch.append(o);
        waiter.waitForEvent(MessageReceivedEvent.class,
                e -> e.getAuthor().equals(event.getAuthor()) && e.getChannel().equals(event.getChannel()) && (e.getMessage().getContentDisplay().equalsIgnoreCase("developer") ^ e.getMessage().getContentDisplay().equalsIgnoreCase("animal") ^ e.getMessage().getContentDisplay().equalsIgnoreCase("school")),
                e -> event.reply("You have chosen the " + e.getMessage().getContentDisplay().replace(this.name, "")+ " theme."+"\nUnscramble this word: "+(e.getMessage().getContentDisplay().replace(this.name, "").equalsIgnoreCase("developer") ? sbDev : (e.getMessage().getContentDisplay().replace(this.name, "").equalsIgnoreCase("animal") ? sbAni : (e.getMessage().getContentDisplay().replace(this.name, "").equalsIgnoreCase("school") ? sbSch : "An error has occured.")))),
                15, TimeUnit.SECONDS, () -> event.reply("Lol, you're too slow. Or you abandoned me :( \nTry again."));
        waiter.waitForEvent(MessageReceivedEvent.class,
                e -> e.getAuthor().equals(event.getAuthor()) && e.getChannel().equals(event.getChannel()) && !e.getMessage().getContentDisplay().contains(this.name) && (!e.getMessage().getContentDisplay().contains("developer") ^ !e.getMessage().getContentDisplay().contains("animal") ^ !e.getMessage().getContentDisplay().contains("school")),
                e -> event.reply(e.getMessage().getContentDisplay().replace(this.name,"").equalsIgnoreCase(developerChosenWord) ^ e.getMessage().getContentDisplay().replace(this.name,"").equalsIgnoreCase(animalChosenWord) ^ e.getMessage().getContentDisplay().replace(this.name,"").equalsIgnoreCase(schoolChosenWord) ? "Correct! You earn nothing." : "Nah, that's wrong. Better luck next time!"),
                30, TimeUnit.SECONDS, () -> event.reply("smh, you are so slow. I gave you 30 seconds and you didn't find it out?"));
    }
}
