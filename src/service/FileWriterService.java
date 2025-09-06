package service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterService {

    private final String filePath = "./resources/words.txt";

    public void addWordToFile(String word) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true))) {
            bufferedWriter.write(word.trim());
            bufferedWriter.newLine();
        }
    }
}