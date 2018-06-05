package ru.kjudge.runner_node.launcher

import ru.kjudge.common.entity.LaunchResult
import ru.kjudge.common.entity.Limits
import ru.kjudge.common.entity.Test
import ru.kjudge.runner_node.entity.*
import java.io.FileReader
import java.io.FileWriter
import java.nio.file.Files
import java.nio.file.Path
import java.util.concurrent.TimeUnit


class JavaImplSolutionLauncher(private val solution: Solution) : SolutionLauncher {

    companion object {
        const val PREFIX = "solution-"
        const val SOURCE_CODE_FILENAME = "source"
        const val PROGRAM_FILENAME = "prog.exe"
    }

    private val tempDir: Path
    private val sourceFileName: String

    init {
        tempDir = createTempDir()
        sourceFileName = SOURCE_CODE_FILENAME + "." + solution.compiler.sourceCodeFileNameExtension
    }

    override fun compile(): LaunchResult {
        val commands = solution.compiler.command
                .replace("#INPUT", sourceFileName)
                .replace("#OUTPUT", PROGRAM_FILENAME).split(" ")

        val source = Files.createFile(tempDir.resolve(sourceFileName)).toFile()
        val writer = FileWriter(source)
        writer.write(solution.code)
        writer.close()

        return launch(commands, Limits(20000, 100, 1024), null,
                inputFileName = "input_compiler", outputFileName = "output_compiler", errorFileName = "error_compiler")
    }

    override fun run(test: Test): LaunchResult {
        val commands = solution.compiler.launchCommand.replace("#OUTPUT", PROGRAM_FILENAME).split(" ")
        return launch(commands, test.limits, test.test)
    }

    override fun runAll(tests: List<Test>): List<LaunchResult> = tests.map { run(it) }

    override fun clear(): Boolean = Files.deleteIfExists(tempDir)

    override fun getSolutionDir(): Path = tempDir


    private fun createTempDir(): Path = Files.createTempDirectory(PREFIX)

    private fun launch(commands: List<String>, limits: Limits, input: String?, inputFileName: String = "input",
                       outputFileName: String = "output", errorFileName: String = "error"): LaunchResult {
        val processBuilder = ProcessBuilder()
        processBuilder.command(commands)

        if (tempDir.resolve(inputFileName).toFile().exists())
            tempDir.resolve(inputFileName).toFile().delete()
        val inputFile = Files.createFile(tempDir.resolve(inputFileName)).toFile()
        val outputFile = tempDir.resolve(outputFileName).toFile()
        val errorFile = tempDir.resolve(errorFileName).toFile()

        val writer = FileWriter(inputFile)
        writer.write(input ?: "")
        writer.close()

        processBuilder.redirectInput(inputFile)
        processBuilder.redirectOutput(outputFile)
        processBuilder.redirectError(errorFile)

        processBuilder.directory(tempDir.toFile())

        val process = processBuilder.start()
        val isExited = process.waitFor(limits.time, TimeUnit.MILLISECONDS)
        if (!isExited)
            process.destroyForcibly()

        val output = FileReader(outputFile).readText()
        val error = FileReader(errorFile).readText()
        return LaunchResult(process.exitValue(), output, error, 0, 0, 0)
    }
}