package ru.kjudge.runner_node.entity

import javax.swing.filechooser.FileNameExtensionFilter

class Compiler(
        val name: String,
        val description: String,
        val command: String,
        val launchCommand: String,
        val sourceCodeFileNameExtension: String
)
