package ru.kjudge.runner_node

import org.slf4j.Logger
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.kjudge.runner_node.data.Message
import ru.kjudge.runner_node.data.RunResult
import ru.kjudge.runner_node.entity.Compiler
import ru.kjudge.runner_node.entity.Solution
import ru.kjudge.runner_node.launcher.JavaImplSolutionLauncher
import ru.kjudge.runner_node.launcher.SolutionLauncher

@Service
class Server {
    private val log: Logger = LoggerFactory.getLogger(Server::class.java.name)

    @Autowired
    lateinit var compilers: List<Compiler>

    @RabbitListener(queues = ["kjudge.runner.requests"])
    fun run(message: Message): RunResult {
        log.info("New task ${message.runId}...")

        val compiler = compilers.firstOrNull { it.shortName == message.compilerName }
                ?: return RunResult("Compiler not found", 201, emptyList())
                        .also { log.error("Compiler '${message.compilerName}' for run id ${message.runId} is not found!") }

        log.info("Trying to compile ${message.runId}")
        val solution = Solution(message.code, compiler)
        val solutionLauncher: SolutionLauncher = JavaImplSolutionLauncher(solution)

        log.info("New folder created for solution ${message.runId}: ${solutionLauncher.getSolutionDir()}")

        val compileResult = solutionLauncher.compile()
        if (compileResult.exitCode != 0) {
            log.info("Compilation error for ${message.runId}")
            return RunResult("Compilation error", 301, listOf(compileResult))
        }

        val testsResult = solutionLauncher.runAll(message.tests)
        return RunResult("Success", 101, testsResult)
    }
}