package service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileLoader {

    private final String filePath;
    private List<String> words = new ArrayList<>();

    public FileLoader(String filePath) {
        this.filePath = filePath;
    }

    public void readWordsFromFile() throws IOException {

        List<String> wordsList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {

            String stringName;

            while ((stringName = bufferedReader.readLine()) != null) {
                if (!stringName.trim().isEmpty()) {
                    wordsList.add(stringName.trim().toLowerCase());
                }
            }
        } catch (FileNotFoundException e) {
            throw new IOException("The file cannot be find " + filePath, e);
        } catch (IOException e) {
            throw new IOException("An error occurred while reading the file " + filePath, e);
        }
        this.words = wordsList;
    }

    public List<String> getWordsList() {
        return new ArrayList<>(words);
    }
}