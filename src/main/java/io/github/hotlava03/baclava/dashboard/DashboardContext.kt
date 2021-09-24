package io.github.hotlava03.baclava.dashboard

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

private lateinit var context: ApplicationContext

fun <T : Any> getBean(beanClass: Class<T>): T {
    return context.getBean(beanClass)
}

@Component
open class DashboardContext : ApplicationContextAware {
    override fun setApplicationContext(applicationContext: ApplicationContext) {
        context = applicationContext
    }
}