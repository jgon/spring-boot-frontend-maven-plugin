package com.github.albanseurat.springboot.plugin;

import static java.lang.String.format;

import java.io.File;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import com.github.albanseurat.springboot.plugin.executors.NpmExecutor;

/**
 * Created by Alban on 15/08/2015.
 */
public class NpmDependencies {

    private final File workingDirectory;
    private final NpmExecutor npmExecutor;

    public NpmDependencies(File workingDirectory) {
        this.workingDirectory = workingDirectory;
        npmExecutor = new NpmExecutor(workingDirectory);
    }

    public void checkExistsAndInstall(String artefact, String version) throws MojoFailureException, MojoExecutionException {
        File depDirectory = new File(workingDirectory, format("node_modules%c%s", File.separatorChar, artefact));
        if(!depDirectory.exists() || !npmExecutor.listDependency(artefact).contains(version)) {
             npmExecutor.installDependency(artefact, version);
        }
    }
}
