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
//    val compiler = Compiler(name = "GCC", description = "desc",
//            command = "/usr/bin/gcc -O3 #INPUT -o #OUTPUT", launchCommand = "./#OUTPUT",
//            sourceCodeFileNameExtension = "cpp")
//    val code = """
//        #include <stdio.h>
//        int main() {
//            while(1) {
//                printf("Lol, kek, cheburek\n");
//            }
//            return 0;
//        }
//    """.trimIndent()
//    val solutionLauncher: SolutionLauncher = JavaImplSolutionLauncher(Solution(code, compiler))
//    val compileResult = solutionLauncher.compile()
//    if (compileResult.exitCode == 0) {
//        println("Successfully compiled! Running...")
//        solutionLauncher.run(Test("", Limits(1000, 100, 1024)))
//    }
    runApplication<RunnerNodeApplication>(*args)
}

