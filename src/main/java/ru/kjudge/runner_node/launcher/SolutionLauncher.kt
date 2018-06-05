package ru.kjudge.runner_node.launcher

import ru.kjudge.runner_node.entity.LaunchResult
import ru.kjudge.runner_node.entity.Test
import java.nio.file.Path

/**
 * Solution launcher interface. You should create instance of this class every time you want to run user's solution
 */
interface SolutionLauncher {

    /**
     * Compile provided solution
     * @return result of compilation (errors, status code)
     */
    fun compile(): LaunchResult

    /**
     * Run compiled solution on test with limits
     * @return result of execution
     */
    fun run(test: Test): LaunchResult

    /**
     * Run all tests
     * @return results of execution
     */
    fun runAll(tests: List<Test>): List<LaunchResult>

    /**
     * Delete source file and compiled solution
     */
    fun clear(): Boolean

    fun getSolutionDir(): Path
}