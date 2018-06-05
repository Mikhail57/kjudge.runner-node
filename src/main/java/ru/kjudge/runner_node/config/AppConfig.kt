package ru.kjudge.runner_node.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Bean
import ru.kjudge.runner_node.entity.Compiler


@Configuration
class AppConfig {
    @Bean
    fun availableCompilers(): List<Compiler> {
        return listOf(
                Compiler(name = "GCC", shortName = "gcc", description = "GNU C Compiler",
                        command = "g++ -O3 #INPUT -o #OUTPUT", launchCommand = "./#OUTPUT",
                        sourceCodeFileNameExtension = "cpp")
        )
    }
}