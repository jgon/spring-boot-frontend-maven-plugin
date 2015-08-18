package com.github.albanseurat.springboot.plugin;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import com.github.albanseurat.springboot.plugin.executors.GruntExecutor;

/**
 * Created by Alban on 15/08/2015.
 */
public class GruntTaskRunner {

    public static final String DEFAULT_GRUNT_CONF = "Gruntfile.js";
    public static final String TMP_DIR = ".tmp";
    private final File gruntFile;
    private final GruntExecutor gruntExecutor;
    private final File workingDirectory;

    public GruntTaskRunner(File workingDirectory, Optional<File> gruntFileRef) {
        this.workingDirectory = workingDirectory;
        this.gruntFile = gruntFileRef.orElseGet(this::defaultGruntFile);
        gruntExecutor = new GruntExecutor(workingDirectory);
    }

    private File defaultGruntFile() {
        try {
            Path tmpDir = Paths.get(workingDirectory.getAbsolutePath(), TMP_DIR);
            if(!Files.exists(tmpDir)) {
                tmpDir = Files.createDirectory(tmpDir);
            }
            Path target = tmpDir.resolve(DEFAULT_GRUNT_CONF);
            Files.copy(this.getClass().getResourceAsStream(File.separator + DEFAULT_GRUNT_CONF), target, REPLACE_EXISTING);
            return target.toFile();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void runTask(String task) throws MojoFailureException, MojoExecutionException {
        gruntExecutor.runTask(gruntFile, task);
    }
}
