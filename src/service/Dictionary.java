package service;

import java.io.IOException;
import java.util.*;

import static java.lang.System.*;

public class Dictionary {

    private final Random random = new Random();
    private List<String> words;
    private final FileWriter fileWriter;

    public Dictionary(List<String> words, FileWriter fileWriter) {
        this.words = words;
        this.fileWriter = fileWriter;
    }

    public String getRandomWord() throws IOException {
        if (words == null || words.isEmpty()) {
            throw new IOException("The words list cannot be empty! ");
        }
        return words.get(random.nextInt(words.size()));
    }

    public void addWord() {

        String inputNewWord = Dialog.inputString(
                "ВВедите слово на кириллице, не менее 6 букв",
                "Слово слишком короткое или написано не по-русски, попробуйте снова ",
                "[А-Яа-яЁё]{6,}");

        if (words.contains(inputNewWord)) {
            out.println("Такое слово уже есть, вы не можете его добавить ");
            return;
        }

        words.add(inputNewWord);

        if (fileWriter != null) {
            try {
                fileWriter.addWordToFile(inputNewWord);
                out.println("Слово успешно добавлено в словарь ");
            } catch (IOException e) {
                out.println("There was an error adding the word" + e);
            }
        }
    }
}