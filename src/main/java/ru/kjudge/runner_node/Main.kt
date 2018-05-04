package ru.kjudge.runner_node

import com.sun.jna.Native
import ru.kjudge.runner_node.native.Launcher


fun main(args: Array<String>) {
    val lib = Native.loadLibrary("kjudge_core", Launcher::class.java)
    println(lib.create_test_dir("/tmp"))
}

