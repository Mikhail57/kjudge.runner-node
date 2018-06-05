package ru.kjudge.runner_node

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import ru.kjudge.runner_node.entity.Compiler
import ru.kjudge.runner_node.entity.Limits
import ru.kjudge.runner_node.entity.Solution
import ru.kjudge.runner_node.entity.Test
import ru.kjudge.runner_node.launcher.JavaImplSolutionLauncher
import ru.kjudge.runner_node.launcher.SolutionLauncher

@SpringBootApplication
class RunnerNodeApplication

fun main(args: Array<String>) {
    runApplication<RunnerNodeApplication>(*args)
}

