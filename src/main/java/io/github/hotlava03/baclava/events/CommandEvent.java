/*
 *    Copyright 2019 HotLava03
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.github.hotlava03.baclava.events;

import io.github.hotlava03.baclava.Baclava;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageUpdateEvent;

/**
 * <p>
 *     The event given as argument onCommand. Triggered by either
 *     MessageReceivedEvents or MessageUpdateEvents in case the command
 *     names match. Do not instantiate this class if you don't know
 *     what you're doing.
 * </p>
 *
 * {@link io.github.hotlava03.baclava.commands.Command#onCommand(CommandEvent)}
 */
public class CommandEvent {

    private GuildMessageReceivedEvent receivedEvent;
    private GuildMessageUpdateEvent updateEvent;
    private User user;
    private Member member;
    private Guild guild;
    private Message message;
    private MessageChannel channel;
    private String label;
    private String commandPrimaryName;
    private String argsRaw;
    private String[] args;
    private JDA jda;


    /**
     * <p>
     *     Use this constructor when the event was triggered by a sent
     *     message.
     * </p>
     *
     * @param e MessageReceivedEvent that triggered the command to run.
     */
    public CommandEvent(GuildMessageReceivedEvent e) {
        this.receivedEvent = e;
        this.user = e.getAuthor();
        this.member = e.getMember();
        this.guild = e.getGuild();
        this.message = e.getMessage();
        this.channel = e.getChannel();
        this.label = e.getMessage().getContentRaw().split("\\s")[0]
                .replaceFirst(Baclava.getPrefix(), "");
        this.commandPrimaryName = Baclava.getRegisteredCommands().getCommandPrimaryName(this.label);
        this.argsRaw = e.getMessage().getContentRaw().replace(Baclava.getPrefix() + label, "").replaceFirst(" ","").trim();
        this.args = argsRaw.split("\\s");
        if (args.length == 1)
            if (args[0].equals(""))
                args = new String[]{};
        this.jda = e.getJDA();
    }

    /**
     * <p>
     *     Use this constructor when the event was triggered by an
     *     edited message.
     * </p>
     *
     * @param e MessageUpdateEvent (edit event) that triggered the command to run.
     */
    public CommandEvent(GuildMessageUpdateEvent e) {
        this.updateEvent = e;
        this.user = e.getAuthor();
        this.member = e.getMember();
        this.guild = e.getGuild();
        this.message = e.getMessage();
        this.channel = e.getChannel();
        this.label = e.getMessage().getContentRaw().split("\\s")[0]
                .replaceFirst(Baclava.getPrefix(), "");
        this.commandPrimaryName = Baclava.getRegisteredCommands().getCommandPrimaryName(this.label);
        this.argsRaw = e.getMessage().getContentRaw().replace(Baclava.getPrefix() + label, "").replaceFirst(" ","").trim();
        this.args = argsRaw.split("\\s");
        this.jda = e.getJDA();
    }

    // Getters

    /**
     * @return The user who executed the command.
     */
    public User getUser() {
        return this.user;
    }

    /**
     * @return The guild member who executed the command.
     */
    public Member getMember() {
        return this.member;
    }

    /**
     * @return The guild where this command was executed.
     */
    public Guild getGuild() {
        return this.guild;
    }

    /**
     * @return The message that contains the command and its arguments.
     */
    public Message getMessage() {
        return this.message;
    }

    /**
     * @return The command name. DOES NOT HAVE THE PREFIX! The name may be an alias, it is basically what the user has ran.
     */
    public String getCommandLabel() {
        return this.label;
    }

    /**
     * @return The raw content of the message without prefix or the command name.
     */
    public String getArgsRaw() {
        return this.argsRaw;
    }

    /**
     * @return The command's arguments.
     */
    public String[] getArgs() {
        return this.args;
    }

    /**
     * <p>
     *     You should retrieve this after verifying if the event is triggered by an edit
     *     or an actual sent message. CommandEvent#isAnEdit
     * </p>
     * @return The message received event triggered by the sent command initially.
     */
    public GuildMessageReceivedEvent getReceivedEvent() {
        return this.receivedEvent;
    }

    /**
     * <p>
     *     You should retrieve this after verifying if the event is triggered by an edit
     *     or an actual sent message. CommandEvent#isAnEdit
     * </p>
     * @return The message edit event triggered in case the command comes from an edited message.
     */
    public GuildMessageUpdateEvent getEditEvent() {
        return this.updateEvent;
    }

    /**
     * @return The JDA interface
     */
    public JDA getJDA() {
        return jda;
    }

    /**
     * @return The MessageChannel where the command was ran.
     */
    public MessageChannel getChannel() {
        return channel;
    }

    /**
     * @return The command's primary name
     */
    public String getCommandPrimaryName() {
        return commandPrimaryName;
    }

    // Sending messages quickly

    /**
     * <p>
     *     Send a message to the channel where the user ran the command.
     *     Type can be any object and will be converted to String.
     * </p>
     *
     * @param msg What is sent to the channel.
     */
    public void reply(Object msg) {
        this.channel.sendMessage(msg.toString()).queue();
    }

    /**
     * <p>
     *     Send a message to the channel where the user ran the command.
     *     Type must be an int and will be converted to String.
     * </p>
     *
     * @param num What is sent to the channel.
     */
    public void reply(int num) {
        this.channel.sendMessage(String.valueOf(num)).queue();
    }

    /**
     * <p>
     *      Send a message to the channel where the user ran the command.
     *      Type must be a CharSequence.
     * </p>
     *
     * @param sequence CharSequence sent with MessageChannel#sendMessage(CharSequence sequence)
     */
    public void reply(CharSequence sequence) {
        this.channel.sendMessage(sequence).queue();
    }

    /**
     * <p>
     *     Send an embed message to the channel where the user ran the command.
     *     Type must be a MessageEmbed.
     * </p>
     *
     * @param embed The built EmbedBuilder. Retrieved with EmbedBuilder#build();
     */
    public void replyEmbed(MessageEmbed embed) {
        this.channel.sendMessage(embed).queue();
    }

    /**
     * <p>
     *     Used to verify if you should retrieve the MessageReceivedEvent
     *     or the MessageUpdateEvent, if needed.
     * </p>
     *
     * @return True if the message was edited or false if it wasn't.
     */
    public boolean isAnEdit() {
        return this.receivedEvent == null;
    }
}
