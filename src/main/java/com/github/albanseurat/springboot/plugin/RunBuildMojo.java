package com.github.albanseurat.springboot.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;

import static java.util.Optional.ofNullable;

@Mojo(name = "build", defaultPhase = LifecyclePhase.COMPILE)
public class RunBuildMojo extends AbstractMojo {

    @Parameter(defaultValue = "${basedir}/src/main/web", property = "workingDirectory", required = false)
    protected File workingDirectory;

    @Parameter(property = "gruntFile", readonly = false)
    protected String gruntFile;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        final GruntTaskRunner taskRunner = new GruntTaskRunner(workingDirectory, ofNullable(gruntFile).map(File::new));
        taskRunner.runTask("default");
    }
}
