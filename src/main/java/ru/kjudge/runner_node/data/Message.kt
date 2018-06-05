package ru.kjudge.runner_node.data

import ru.kjudge.runner_node.entity.Test

data class Message(
        val runId: Long,
        val compilerName: String,
        val code: String,
        val tests: List<Test>
)