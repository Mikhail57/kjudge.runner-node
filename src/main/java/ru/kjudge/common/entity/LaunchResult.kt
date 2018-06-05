package ru.kjudge.common.entity

data class LaunchResult(
        val exitCode: Int,
        val output: String,
        val errorOutput: String,
        val executionTime: Long,
        // Will be used in future releases
        val memoryUsage: Long,
        val cpuUsage: Long
)