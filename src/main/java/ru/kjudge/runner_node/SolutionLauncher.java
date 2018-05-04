package ru.kjudge.runner_node;

public class SolutionLauncher {

    private native void init();
    private native String createTempDirectory(String path);
    private native void deleteTempDirectory(String path);
    private String compileSolution() {
        return "";
    }
    private native String checkSolution();
    private String createTempDirectory() {
        return createTempDirectory("/tmp");
    }
}
