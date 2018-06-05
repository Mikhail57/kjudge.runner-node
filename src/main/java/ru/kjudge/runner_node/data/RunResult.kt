package ru.kjudge.runner_node.data

import ru.kjudge.runner_node.entity.LaunchResult

data class RunResult(
        val status: String,
        val statusCode: Int,
        val results: List<LaunchResult>
)