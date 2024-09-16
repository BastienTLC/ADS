package se.umu.cs.ads.a1.util;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class CsvMeasurementWriter {
    private final String baseDirectory = "measurements";
    private final String className;
    private final String executionTimestamp;
    private final String executionDirectory;
    private final HashMap<String, Boolean> headerWritten;
    private final HashMap<String, FileWriter> fileWriters;

    public CsvMeasurementWriter(String className) {
        this.className = className;
        this.executionTimestamp = String.valueOf(System.currentTimeMillis());
        this.executionDirectory = baseDirectory + "/" + className + "/" + executionTimestamp;
        createExecutionDirectory();
        this.headerWritten = new HashMap<>();
        this.fileWriters = new HashMap<>();
    }

    private void createExecutionDirectory() {
        try {
            Files.createDirectories(Paths.get(executionDirectory));
        } catch (IOException e) {
            throw new RuntimeException("Failed to create directory", e);
        }
    }

    private FileWriter getFileWriter(String functionName) {
        if (fileWriters.containsKey(functionName)) {
            return fileWriters.get(functionName);
        } else {
            String filePath = executionDirectory + "/" + functionName + ".csv";
            try {
                FileWriter writer = new FileWriter(filePath, true);
                fileWriters.put(functionName, writer);
                headerWritten.put(functionName, false);
                return writer;
            } catch (IOException e) {
                throw new RuntimeException("Error creating file for " + filePath, e);
            }
        }
    }

    public void writeMeasurement(String functionName, String[] headers, String[] values) {
        if (headers.length != values.length) {
            throw new IllegalArgumentException("Headers and values must have the same length");
        }

        try {
            FileWriter writer = getFileWriter(functionName);
            if (!headerWritten.get(functionName)) {
                writer.write(String.join(",", headers) + "\n");
                headerWritten.put(functionName, true);
            }
            writer.write(String.join(",", values) + "\n");
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException("Error writing measurement for function " + functionName, e);
        }
    }

    public void close() {
        for (FileWriter writer : fileWriters.values()) {
            try {
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException("Error closing FileWriter", e);
            }
        }
    }
}
