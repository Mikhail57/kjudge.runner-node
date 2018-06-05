package ru.kjudge.runner_node.config

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.kjudge.runner_node.Server

@Configuration
class RabbitConfig {
    @Bean
    fun queue(): Queue {
        return Queue("kjudge.runner.requests")
    }

    @Bean
    fun exchange(): DirectExchange {
        return DirectExchange("kjudge.runner")
    }

    @Bean
    fun binding(exchange: DirectExchange, queue: Queue): Binding {
        return BindingBuilder.bind(queue).to(exchange).with("rpc")
    }

    @Bean
    fun server() = Server()

    @Bean
    fun jsonMessageConverter(): MessageConverter = Jackson2JsonMessageConverter(jacksonObjectMapper())
}