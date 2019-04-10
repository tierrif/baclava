# Baclava
A discord bot that is made to annoy you, and sucks by default. It is written in Java, using the JDA library.


\n
# Docs
Remember: This bot is not a release **yet**. 
  **Commands:**
    -> `>>help` - Sends a help message in DMs.
    -> `>>ping` - Sends an embed with the bot ping.
    -> `>>info` - Some stats about the bot.
    -> `>>shuffle` - Shuffles a determined amount of Strings given in the command arguments. Useful for team making, reliable if you look at the source. (I know the code sucks but ok it works for now)
    -> `>>react` - Give a message ID in the first param, and an emote name in the second one. The emote name MUST exist in the guild, and if anything is a dupe for some reason, the first found name will be the used one. This will react the given message (by ID) with the given emote name.
    -> `>>say` - Basically the arguments are the bot's message. The name says it all, the bot will say what you want it to.
    -> `>>suggest` - Sends a DM to the bot owner. Be careful, change this in the Main.java class, as there is a global variable called `OwnerID`.
 **Events:**
    -> Saying `baclava` in any sentence will make the bot ask what the hell do you want. Yes the bot gets annoyed easily.
    -> Say just `0` or `zero` will meme your message with a cool reaction.


