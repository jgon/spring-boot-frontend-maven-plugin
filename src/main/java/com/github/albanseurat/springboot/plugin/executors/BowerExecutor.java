package com.github.albanseurat.springboot.plugin.executors;

import static java.lang.String.format;

import java.io.File;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BowerExecutor implements TaskExecutor {

    private ProcessBuilder processBuilder;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public BowerExecutor(File workingDirectory) {
        processBuilder = new ProcessBuilder().directory(workingDirectory);
    }

    public void installDependency(String artefact, String version) throws MojoExecutionException, MojoFailureException {
        this.executeWithBuilder(
                processBuilder.command(format("node_modules/bower/bin/bower install %s#%s", artefact, version).split(" ")),
                logger);
    }

    public ProcessResult listDependency(String artefact) throws MojoExecutionException, MojoFailureException {
        return this.executeAndGetResult(
                processBuilder.command(format("node_modules/bower/bin/bower cache list %s", artefact).split(" ")), logger);
    }
}
