package io.github.hotlava03.baclava.config

class Config(
    val baseUri: String,
    val prefix: String,
    val conversationTimeout: Long,
    val baclavaColor: String,
    val owners: Array<String>,
    val clientSecret: String,
    val clientId: String,
    val redisHostName: String,
    val redisPort: Int,
    val redisDatabase: Int,
    val redisPassword: String,
    val aiFailureMessage: String,
    val frontendUri: String,
    val ticTacToeTimeout: Long,
)

