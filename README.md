# Baclava
A Discord bot that talks to you like a human. Despite its name, it can be your lover, your friend, or even your parent. It has nothing to do with food (sadly).

## Disclaimer
This project is still a WIP. It does not work. When a minimally working version exists, this paragraph will be instantly removed.

## About
Baclava is written with [JDA](https://github.com/DV8FromTheWorld/JDA), in [Kotlin](https://kotlinlang.org/) for the bot part, and [Spring](https://spring.io/), also with [Kotlin](https://kotlinlang.org/), for the dashboard's backend. The dashboard's backend starts in the same process as the bot for the sake of simplicity.

## Building
Simply clone this repository, run `./gradlew shadowJar` and you'll have a compiled jar file in `build/libs`.

## Running
You will need a `config.json` file in the same directory level as the jar file you are going to run. Here's an example:

```json
{
  "baseUri": "http(s)://localhost:port/",
  "prefix": ">>",
  "conversationTimeout": 600000
}
```

`conversationTimeout` is the timeout in milliseconds (default is 10 minutes) to expire a conversation's context. This means that when 10 minutes pass, the context of previous conversations will be reset.

**Important**: The `baseUrl` must include a `/` in the end. Make sure you have the correct protocol and port.

Then, you'll need to set 2 environment variables. First, `TOKEN`, with the bot's token, and second `CLIENT_SECRET`, which holds the client secret of the bot. This is for OAuth2, which isn't required by bots usually, but by other types of application. OAuth2 is used for logging into the dashboard.

After this is done, run the server with `java -jar JarFileName.jar --server.port=YOUR_PORT_HERE`. Replace `YOUR_PORT_HERE` with the port you desire to use for the Spring server. 
 
# License
This software is licensed under the Apache License 2.0. More information [here](https://github.com/HotLava03/Baclava/blob/master/LICENSE).
