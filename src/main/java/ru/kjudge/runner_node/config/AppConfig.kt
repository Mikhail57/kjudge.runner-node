package ru.kjudge.runner_node.config

import org.springframework.amqp.core.Binding
import org.springframework.context.annotation.Configuration
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.context.annotation.Bean
import ru.kjudge.runner_node.Server
import ru.kjudge.runner_node.entity.Compiler


@Configuration
class AppConfig {
    @Bean
    fun availableCompilers(): List<Compiler> {
        return listOf(
                Compiler(name = "GCC", shortName = "gcc", description = "GNU C Compiler",
                        command = "gcc -O3 #INPUT -o #OUTPUT", launchCommand = "./#OUTPUT",
                        sourceCodeFileNameExtension = "cpp")
        )
    }
}