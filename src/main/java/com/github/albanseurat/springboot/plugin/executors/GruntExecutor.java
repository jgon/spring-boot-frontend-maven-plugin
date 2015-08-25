package com.github.albanseurat.springboot.plugin.executors;

import static java.lang.String.format;

import java.io.File;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GruntExecutor implements TaskExecutor {

    private ProcessBuilder processBuilder;
    private File workingDirectory;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public GruntExecutor(File workingDirectory) {
        this.workingDirectory = workingDirectory;
        this.processBuilder = new ProcessBuilder().directory(workingDirectory);
    }

    public void runTask(File gruntFile, String task) throws MojoFailureException, MojoExecutionException {
        this.executeWithBuilder(
                processBuilder.command(format("node_modules/grunt-cli/bin/grunt --base %s --gruntfile %s %s",
                        workingDirectory.getAbsolutePath(), gruntFile.getAbsolutePath(), task).split(" ")),
                logger);
    }
}
