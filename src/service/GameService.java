
package service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static java.lang.System.*;

public class GameService {

    private final DictionaryService dictionaryService;
    private final FileLoaderService fileLoaderService;
    private final Scanner scanner;

    public int mistakesCount;
    public static int MAX_MISTAKES = 7;

    public GameService(DictionaryService dictionaryService, FileLoaderService fileLoaderService, Scanner scanner) {
        this.dictionaryService = dictionaryService;
        this.fileLoaderService = fileLoaderService;
        this.scanner = scanner;
    }

    private void printHearts(int mistakesCount) {
        String redHeart = "\u2764";
        int livesAvailable = MAX_MISTAKES - mistakesCount;
        StringBuilder lives = new StringBuilder();
        for (int i = 0; i < livesAvailable; i++) {
            lives.append(redHeart).append(" ");
        }
        out.println("Жизней -> " + lives);
    }

    public void startGame() {
        String secretWord;

        try {
            secretWord = dictionaryService.getRandomWord();
        } catch (IOException e) {
            out.println("Ошибка чтения слова из словаря ");
            return;
        }

        Set<Character> guessedLetter = new HashSet<>();

        mistakesCount = 0;

        out.println("Добро пожаловать в игру, игра начинается:\n" +
                "Попробуйте угадать слово - " + dictionaryService.maskWord(secretWord));

        while (mistakesCount < MAX_MISTAKES) {

            out.println("Введите букву");
            String input = scanner.nextLine().toLowerCase().trim();

            char guess = input.charAt(0);

            if (input.length() != 1 || !input.matches("[А-Яа-я]")) {
                out.println("Введите только одну букву Кириллицы");
                continue;
            }
            if (guessedLetter.contains(guess)) {
                out.println("Вы уже вводили данную букву, попробуйте снова");
                continue;
            }

            guessedLetter.add(guess);

            StringBuilder newWordMask = new StringBuilder();
            boolean found = false;

            for (char c : secretWord.toCharArray()) {
                if (guessedLetter.contains(Character.toLowerCase(c))) {
                    newWordMask.append(c);
                    if (Character.toLowerCase(c) == guess) {
                        found = true;
                    }
                } else if (Character.isLetter(c)) {
                    newWordMask.append("*");
                } else {
                    newWordMask.append(c);
                }
            }

            String maskedWord = newWordMask.toString();

            out.println("Слово: " + maskedWord);

            if (!found) {
                mistakesCount++;
                out.println("Такой буквы нет! Жизней осталось: ");
                printHearts(mistakesCount);

                if (mistakesCount >= MAX_MISTAKES) {
                    out.println("Вы проиграли! Секретное слово было: " + secretWord);
                    return;
                }
            } else {
                out.println("Такая буква есть! ");
            }

            if (maskedWord.equals(secretWord)) {
                out.println("Вы победили, секретное слово -> " + secretWord.toUpperCase());
                return;
            }
        }
    }
}