package ru.kjudge.common.entity

data class Limits(
        /**
         * Real time limits in milliseconds
         */
        val time: Long,
        // TODO: use this limits
        val cpu: Long,
        val ram: Long
)