package ru.kjudge.runner_node.native

import com.sun.jna.Library

interface Launcher : Library {
    fun ktest_init(): Boolean
    fun create_test_dir(path: String): String
    fun delete_test_dir(path: String): Boolean
}