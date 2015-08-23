package com.github.albanseurat.springboot.plugin;

import static org.apache.commons.io.FileUtils.deleteDirectory;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.apache.maven.plugin.testing.MojoRule;
import org.junit.Rule;
import org.junit.Test;

public class FrontendMojoTest {

    private File workingDirectory = new File( "src/test/resources/simple-project");
    private File resourcesTargetDirectory = new File(workingDirectory, "resources");
    private File tmpDirectory = new File(workingDirectory, "src/main/web/.tmp");
    private File nodeDirectory = new File(workingDirectory, "src/main/web/node_modules");
    private File bowerDirectory = new File(workingDirectory, "src/main/web/bower_components");

    @Rule
    public MojoRule rule = new MojoRule()
    {
        @Override
        protected void before() throws Throwable
        {
            deleteDirectory(resourcesTargetDirectory);
            deleteDirectory(nodeDirectory);
            deleteDirectory(bowerDirectory);
            deleteDirectory(tmpDirectory);
        }

        @Override
        protected void after()
        {

        }
    };

    /**
     * @throws Exception if any
     */
    @Test
    public void testSimpleProject()
        throws Exception
    {
        File pom = new File(workingDirectory, "pom.xml");
        assertNotNull(pom);
        assertTrue(pom.exists());

        PrepareDependenciesMojo prepareDependenciesMojo = (PrepareDependenciesMojo) rule.lookupMojo("prepare-deps", pom );
        assertNotNull(prepareDependenciesMojo);

        prepareDependenciesMojo.execute();

        RunBuildMojo runBuildMojo= (RunBuildMojo) rule.lookupMojo("build", pom );
        assertNotNull(runBuildMojo);

        runBuildMojo.execute();


        assertTrue(nodeDirectory.exists());
        assertTrue(new File(nodeDirectory, "bower").exists());
        assertTrue(new File(nodeDirectory, "grunt").exists());
        assertTrue(new File(nodeDirectory, "grunt-cli").exists());

        assertTrue(new File(bowerDirectory, "angular").exists());
        assertTrue(new File(bowerDirectory, "bootstrap").exists());
        assertTrue(new File(bowerDirectory, "jquery").exists());

    }
}