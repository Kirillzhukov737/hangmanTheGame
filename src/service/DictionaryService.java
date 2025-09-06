package service;

import java.io.IOException;
import java.util.*;

import static java.lang.System.*;

public class DictionaryService {

    private final Random random = new Random();
    private List<String> words;
    private Scanner scanner = new Scanner(in);
    private final FileWriterService fileWriterService;

    public DictionaryService(List<String> words, FileWriterService fileWriterService) {
        this.words = words;
        this.fileWriterService = fileWriterService;
    }

    public String getRandomWord() throws IOException {
        if (words == null || words.isEmpty())
            throw new IOException("Список слов не может быть пустым! ");
        return words.get(random.nextInt(words.size()));
    }

    public String maskWord(String word) {

        StringBuilder stringBuilder = new StringBuilder();

        for (char c : word.toCharArray()) {
            if (Character.isLetter(c)) {
                stringBuilder.append(" * ");
            } else {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }

    public void addWord() {

        while (true) {

            out.println("ВВедите слово на кириллице, не менее 6 букв");
            String inputNewWord = scanner.nextLine().trim().toLowerCase();

            if (inputNewWord.length() < 6 || !inputNewWord.matches("[А-Яа-я]+")) {
                out.println("Слово слишком короткое или написано не по-русски, попробуйте снова ");
                return;
            }
            if (words.contains(inputNewWord)) {
                out.println("Такое слово уже есть, вы не можете его добавить ");
                return;
            }

            words.add(inputNewWord);

            if (fileWriterService != null) {
                try {
                    fileWriterService.addWordToFile(inputNewWord);
                    out.println("Слово успешно добавлено в словарь ");
                } catch (IOException e) {
                    out.println("Произошла ошибка при добавлении слова ");
                }
            }
            break;
        }
    }
}