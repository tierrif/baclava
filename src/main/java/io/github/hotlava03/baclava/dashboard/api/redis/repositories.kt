package io.github.hotlava03.baclava.dashboard.api.redis

import io.github.hotlava03.baclava.dashboard.api.entities.MessageBundle
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MessageRepository : CrudRepository<MessageBundle, String>
