package ru.kjudge.common.entity

import ru.kjudge.common.entity.LaunchResult

data class RunResult(
        val status: String,
        val statusCode: Int,
        val results: List<LaunchResult>
)