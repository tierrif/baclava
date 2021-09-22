package io.github.hotlava03.baclava.config

class Config(
    val baseUri: String,
    val prefix: String,
    val conversationTimeout: Long,
    val baclavaColor: String,
    val owners: Array<String>,
    val clientSecret: String,
    val clientId: String,
)

