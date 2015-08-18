package com.github.albanseurat.springboot.plugin.executors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.slf4j.Logger;

import static java.lang.String.join;

public interface TaskExecutor {

    default void executeWithBuilder(ProcessBuilder processBuilder, Logger logger) throws MojoExecutionException, MojoFailureException {
        try {
            final Process process = processBuilder.start();

            final Thread infoLogThread = InputStreamHandler.logInfo(process.getInputStream(), logger);
            infoLogThread.start();
            final Thread errorLogThread = InputStreamHandler.logError(process.getErrorStream(), logger);
            errorLogThread.start();

            int result = process.waitFor();
            infoLogThread.join();
            errorLogThread.join();
            if (result != 0) {
                throw new MojoFailureException(join(" ", processBuilder.command()) + " failed with error code " + result);
            }
        } catch (IOException | InterruptedException e) {
            throw new MojoExecutionException("exception during run", e);
        }
    }


    default ProcessResult executeAndGetResult(ProcessBuilder processBuilder, Logger logger) throws MojoExecutionException, MojoFailureException {
        try {
            final Process process = processBuilder.start();
            final String result = readString(process.getInputStream());
            final String error = readString(process.getErrorStream());
            final int exitValue = process.waitFor();
            return new ProcessResult(exitValue, result, error);
        } catch (IOException | InterruptedException e) {
            throw new MojoExecutionException("exception during run", e);
        }
    }


    static String readString(InputStream processInputStream) throws IOException {
        BufferedReader inputStream = new BufferedReader(new InputStreamReader(processInputStream));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = inputStream.readLine()) != null) {
            result.append(line).append("\n");
        }
        return result.toString().trim();
    }
}
