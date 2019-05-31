package io.github.hotlava03.baclava.commands.fun;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.github.hotlava03.baclava.Main.ownerID;

public class ArtCmd extends Command {
    public ArtCmd(){
        this.name = "art";
    }
    public void execute(CommandEvent event){
        if(!event.getAuthor().getId().equals(ownerID)) {
            event.reply("Art is for nixxo, unless you ask my owner to run this");
            return;
        }
        TextChannel artChannel = event.getJDA().getTextChannelById(430350067061358592L); // Can get replaced with finding a channel by name containing/equaling "art".
        artChannel.getHistory().retrievePast(100).queue(newMessages -> {
            Collections.shuffle(newMessages);
            String id = newMessages.get(0).getId();
            artChannel.getHistoryBefore(id,100).queue(messages -> {
                messages.retrievePast(100).queue(messagesFinal -> {
                    List<Message> messagesWithAttachent = new ArrayList<>();
                    for(Message message : messagesFinal){
                        if(message.getAttachments().isEmpty())
                            continue;
                        messagesWithAttachent.add(message);
                    }
                    Collections.shuffle(messagesWithAttachent);
                    EmbedBuilder eb = new EmbedBuilder();
                    eb.setTitle("**Art**",null);
                    eb.setImage(messagesWithAttachent.get(0).getAttachments().get(0).getUrl());
                    eb.setDescription("**Author:** "+messagesWithAttachent.get(0).getAuthor().getAsTag()+"\n**Jump to message:** "+messagesWithAttachent.get(0).getJumpUrl());
                    event.reply(eb.build());
                });
            });
        });
    }
}
