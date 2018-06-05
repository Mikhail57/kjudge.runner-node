package ru.kjudge.runner_node

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RunnerNodeApplication

fun main(args: Array<String>) {
    runApplication<RunnerNodeApplication>(*args)
}
