package ru.kjudge.runner_node;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Compiler {
    private String name;
    private String description;
    private String command;
}
