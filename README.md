# Baclava
A discord bot that is made to annoy you, and sucks by default. It is written in Java, using the JDA library.
# Docs
Remember: This bot is not a release **yet**. <br>
  **Commands:**<br>
    -> `>>help` - Sends a help message in DMs.<br>
    -> `>>ping` - Sends an embed with the bot ping.<br>
    -> `>>info` - Some stats about the bot.<br>
    -> `>>shuffle` - Shuffles a determined amount of Strings given in the command arguments. Useful for team making, reliable if you look at the source. (I know the code sucks but ok it works for now)<br>
    -> `>>react` - Give a message ID in the first param, and an emote name in the second one. The emote name MUST exist in the guild, and if anything is a dupe for some reason, the first found name will be the used one. This will react the given message (by ID) with the given emote name.<br>
    -> `>>say` - Basically the arguments are the bot's message. The name says it all, the bot will say what you want it to.<br>
    -> `>>suggest` - Sends a DM to the bot owner. Be careful, change this in the Main.java class, as there is a global variable called `OwnerID`.<br>
 **Events:**<br>
    -> Saying `baclava` in any sentence will make the bot ask what the hell do you want. Yes the bot gets annoyed easily.<br>
    -> Say just `0` or `zero` will meme your message with a cool reaction.<br>
    <br>
# Getting Started
*It is not intended to use this bot as it is, the code inside it is intended to be useful **without any warranties**, but in case you want to try it out, this is what you should do:*<br>
<br>
 **1.** Create a new **GRADLE** project in the <a href="https://www.jetbrains.com/idea/">IntelliJ IDEA IDE</a>.<br>
 **2.** Download all the Java files.<br>
 **3.** Keep the Java files in a similar format as the folders, to keep your work organized.<br>
 **4.** Create new packages, in the same form as the folders are. While creating a package, add all the necessary java files, just as showed in the file configuration.<br>
 **5.** When the whole file configuration is done, don't worry if you find errors. Open the build.gradle file and update it with the content inside the build.gradle file in the source code of this repository.<br>
 **6.** Create/download the config.properties file and put it in the resources folder, same level as java. Add your token in it.<br>
 **7.** In the Main.java file, update your Owner ID in the `OwnerId` string.<br>
 **8.** Open the gradle tab > Tasks > shadow > shadowJar; this will generate a jar file for your bot. **[OPTIONAL]**<br>
 **9.** Try to run the bot.<br><br>
 
 # License
  This software is licensed under the Apache License 2.0. More information <a href=https://github.com/HotLava03/Baclava/blob/master/LICENSE>here</a>.<br>
  Copyright 2019 HotLava03

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
