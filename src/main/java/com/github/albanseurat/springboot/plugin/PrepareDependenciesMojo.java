package com.github.albanseurat.springboot.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;

@Mojo(name = "prepare-deps", defaultPhase = LifecyclePhase.GENERATE_RESOURCES)
public class PrepareDependenciesMojo extends AbstractMojo {


    @Parameter(defaultValue = "${basedir}/src/main/web", property = "workingDirectory", required = false)
    protected File workingDirectory;

    @Parameter(property = "npmDependencies", required = false)
    protected String[] npmDependencies;

    @Parameter(property = "bowerDependencies", required = false)
    protected String[] bowerDependencies;

    public void execute() throws MojoExecutionException, MojoFailureException {

        final NpmDependencies npmDependencies = new NpmDependencies(workingDirectory);

        for (String npmDep : this.npmDependencies == null || this.npmDependencies.length == 0 ? Defaults.npmDependencies() : this.npmDependencies) {
            String[] npmDepDetails = npmDep.split("@");
            npmDependencies.checkExistsAndInstall(npmDepDetails[0], npmDepDetails[1]);
        }

        final BowerDependencies bowerDependencies = new BowerDependencies(workingDirectory);

        for (String bowerDep : this.bowerDependencies == null || this.bowerDependencies.length == 0 ? Defaults.bowerDependencies() : this.bowerDependencies) {
            String[] bowerDepDetails = bowerDep.split("#");
            bowerDependencies.checkExistsAndInstall(bowerDepDetails[0], bowerDepDetails[1]);
        }

    }
}
