package service;

import java.io.BufferedWriter;
import java.io.IOException;

public class FileWriter {

    private final String filePath;

    public FileWriter(String filePath) {
        this.filePath = filePath;
    }

    public void addWordToFile(String word) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter
                (new java.io.FileWriter(filePath, true))) {
            bufferedWriter.write(word.trim());
            bufferedWriter.newLine();
        }
    }
}