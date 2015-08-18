package com.github.albanseurat.springboot.plugin;

import static java.lang.String.format;

import java.io.File;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import com.github.albanseurat.springboot.plugin.executors.BowerExecutor;

/**
 * Created by Alban on 15/08/2015.
 */
public class BowerDependencies {

    private final File workingDirectory;
    private final BowerExecutor bowerExecutor;

    public BowerDependencies(File workingDirectory) {
        this.workingDirectory = workingDirectory;
        bowerExecutor = new BowerExecutor(workingDirectory);
    }

    public void checkExistsAndInstall(String artefact, String version) throws MojoFailureException, MojoExecutionException {
        File depDirectory = new File(workingDirectory, format("bower_components%c%s", File.separatorChar, artefact));
        if(!depDirectory.exists() || !bowerExecutor.listDependency(artefact).contains(version)) {
             bowerExecutor.installDependency(artefact, version);
        }
    }
}
