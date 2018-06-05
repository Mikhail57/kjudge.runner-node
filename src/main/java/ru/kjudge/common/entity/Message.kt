package ru.kjudge.common.entity

import ru.kjudge.common.entity.Test

data class Message(
        val runId: Long,
        val compilerName: String,
        val code: String,
        val tests: List<Test>
)