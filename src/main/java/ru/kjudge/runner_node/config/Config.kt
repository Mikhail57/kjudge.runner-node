package ru.kjudge.runner_node.config

import org.springframework.amqp.core.Binding
import org.springframework.context.annotation.Configuration
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.context.annotation.Bean
import ru.kjudge.runner_node.entity.Compiler


@Configuration
class Config {
    @Bean
    fun queue(): Queue {
        return Queue("tut.rpc.requests")
    }

    @Bean
    fun exchange(): DirectExchange {
        return DirectExchange("tut.rpc")
    }

    @Bean
    fun binding(exchange: DirectExchange, queue: Queue): Binding {
        return BindingBuilder.bind(queue).to(exchange).with("rpc")
    }

    @Bean
    fun availableCompilers(): List<Compiler> {
        return listOf(
                Compiler(name = "GCC", shortName = "gcc", description = "GNU C Compiler",
                        command = "gcc -O3 #INPUT -o #OUTPUT", launchCommand = "./#OUTPUT",
                        sourceCodeFileNameExtension = "cpp")
        )
    }

}