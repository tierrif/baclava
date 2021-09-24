package io.github.hotlava03.baclava.dashboard.api.redis

import io.github.hotlava03.baclava.config.ConfigHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisPassword
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate

@Configuration
open class RedisConfiguration {
    @Bean
    internal open fun jedisConnectionFactory(): JedisConnectionFactory {
        val config = RedisStandaloneConfiguration()
        config.hostName = ConfigHandler.config.redisHostName
        config.port = ConfigHandler.config.redisPort
        config.database = ConfigHandler.config.redisDatabase
        config.password = RedisPassword.of(ConfigHandler.config.redisPassword)

        return JedisConnectionFactory(config)
    }

    @Bean
    open fun redisTemplate(): RedisTemplate<String, Any> {
        val template = RedisTemplate<String, Any>()
        template.connectionFactory = jedisConnectionFactory()
        return template
    }
}