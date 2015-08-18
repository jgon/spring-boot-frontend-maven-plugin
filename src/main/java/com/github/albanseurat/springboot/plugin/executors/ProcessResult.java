package com.github.albanseurat.springboot.plugin.executors;

/**
 * Created by Alban on 15/08/2015.
 */
public class ProcessResult {

    private int returnCode;
    private String stdout;
    private String stderr;

    public ProcessResult(int returnCode, String stdout, String stderr) {
        this.returnCode = returnCode;
        this.stdout = stdout;
        this.stderr = stderr;
    }

    public boolean hasFailed() {
        return returnCode != 0;
    }

    public boolean contains(String version) {
        return stdout.contains(version);
    }
}
