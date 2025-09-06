package service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileLoaderService {

    private final String filePath = "./resources/words.txt";

    List<String> words = new ArrayList<>();

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
            throw new IOException("Не можем прочитать файл " + filePath, e);
        } catch (IOException e) {
            throw new IOException("Произошла ошибка во время чтения файла " + filePath, e);
        }
        this.words = wordsList;
    }

    public List<String> getWordsList() {
        return words;
    }
}