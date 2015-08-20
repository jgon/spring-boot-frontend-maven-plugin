package com.github.albanseurat.springboot.plugin;

import static java.util.Optional.ofNullable;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "frontend", defaultPhase = LifecyclePhase.GENERATE_RESOURCES)
public class FrontendMojo extends AbstractMojo {


    @Parameter(defaultValue = "${basedir}/web", property = "workingDirectory", required = false)
    protected File workingDirectory;

    @Parameter(property = "npmDependencies", required = false)
    protected String[] npmDependencies;


    @Parameter(property = "bowerDependencies", required = false)
    protected String[] bowerDependencies;

    @Parameter(property = "gruntFile", readonly = false)
    protected String gruntFile;


    public void execute() throws MojoExecutionException, MojoFailureException {

        final NpmDependencies npmDependencies = new NpmDependencies(workingDirectory);

        for (String npmDep : this.npmDependencies == null ? Defaults.npmDependencies() : this.npmDependencies) {
            String[] npmDepDetails = npmDep.split("@");
            npmDependencies.checkExistsAndInstall(npmDepDetails[0], npmDepDetails[1]);
        }

        final BowerDependencies bowerDependencies = new BowerDependencies(workingDirectory);

        for (String bowerDep : this.bowerDependencies == null ? Defaults.bowerDependencies() : this.bowerDependencies) {
            String[] bowerDepDetails = bowerDep.split("#");
            bowerDependencies.checkExistsAndInstall(bowerDepDetails[0], bowerDepDetails[1]);
        }

        final GruntTaskRunner taskRunner = new GruntTaskRunner(workingDirectory, ofNullable(gruntFile).map(File::new));
        taskRunner.runTask("default");
    }
}