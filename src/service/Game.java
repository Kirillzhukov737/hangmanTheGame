
package service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.System.*;

public class Game {

    private final Dictionary dictionary;
    private final FileLoader fileLoader;

    private int mistakesCount;
    private static final int MAX_MISTAKES = 7;

    public Game(Dictionary dictionary, FileLoader fileLoader) {
        this.dictionary = dictionary;
        this.fileLoader = fileLoader;
    }

    public void startGame() {
        Player player = Player.choisePlayer();

        String secretWord;

        try {
            secretWord = dictionary.getRandomWord();
        } catch (IOException e) {
            out.println("Error reading a word from a dictionary " + e);
            return;
        }

        Set<Character> guessedLetters = new HashSet<>();

        mistakesCount = 0;

        String mask = maskWord(secretWord);

        out.println("Добро пожаловать в игру, игра начинается:");
        out.println("Попробуйте угадать слово - " + mask);

        while (!isLose()) {
            printGuessedLetters(guessedLetters);

            char guess = askLetter(guessedLetters);

            String maskedWord = updateMaskedWord(secretWord, guessedLetters);
            out.println("Слово: " + maskedWord);

            if (!isCorrectGuess(secretWord, guess)) {
                mistakesCount++;
                out.println("Такой буквы нет! Жизней осталось: ");
                printHangman(mistakesCount);
                printHearts(mistakesCount);
            } else {
                out.println("Такая буква есть!");
            }

            if (isWin(maskedWord, secretWord)) {
                out.println("Поздравляю, Вы победили! Секретное слово -> "
                        + secretWord.toLowerCase().trim());
                return;
            }
        }
        out.println("Вы проиграли, секретное слово было -> " + secretWord);
    }

    private void printGuessedLetters(Set<Character> guessedLetters) {
        if (!guessedLetters.isEmpty()) {
            out.println("Введенные буквы: ");
            guessedLetters.forEach(c -> out.print(c + " "));
            out.println();
        }
    }

    private char askLetter(Set<Character> guessedLetters) {
        while (true) {
            String input = Dialog.inputString(
                    "Введите одну букву Кириллицы",
                    "Ошибка ввода ",
                    "[А-Яа-яЁё]{1}"
            ).trim().toLowerCase();

            char guess = input.charAt(0);

            if (guessedLetters.contains(guess)) {
                out.println("Вы уже вводили данную букву, попробуйте снова ");
            } else {
                guessedLetters.add(guess);
                return guess;
            }
        }
    }

    private String maskWord(String word) {

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

    private String updateMaskedWord(String secretWord, Set<Character> guessedLetters) {

        StringBuilder newWordMask = new StringBuilder();

        for (char c : secretWord.toCharArray()) {
            if (guessedLetters.contains(Character.toLowerCase(c))) {
                newWordMask.append(c);
            } else if (Character.isLetter(c)) {
                newWordMask.append("*");
            } else {
                newWordMask.append(c);
            }
        }
        return newWordMask.toString();
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

    private void printHangman(int mistakesCount) {
        List<String> hangman = List.of(
                "_______+\n " +
                        " |        \n" +
                        " |        \n" +
                        " |        \n" +
                        " |        \n" +
                        " |        \n" +
                        " |        \n" +
                        "________  \n",
                "_______+\n " +
                        " |      | \n" +
                        " |        \n" +
                        " |        \n" +
                        " |        \n" +
                        " |        \n" +
                        " |        \n" +
                        "________  \n",
                "_______+\n " +
                        " |      | \n" +
                        " |      0 \n" +
                        " |        \n" +
                        " |        \n" +
                        " |        \n" +
                        " |        \n" +
                        "________  \n",
                "_______+\n " +
                        " |      | \n" +
                        " |      0 \n" +
                        " |      | \n" +
                        " |        \n" +
                        " |        \n" +
                        " |        \n" +
                        "________  \n",
                "_______+\n " +
                        " |      | \n" +
                        " |      0 \n" +
                        " |     /| \n" +
                        " |        \n" +
                        " |        \n" +
                        " |        \n" +
                        "________  \n",
                "_______+\n " +
                        " |      | \n" +
                        " |      0 \n" +
                        " |     /||\n" +
                        " |        \n" +
                        " |        \n" +
                        " |        \n" +
                        "________  \n",
                "_______+\n " +
                        " |      | \n" +
                        " |      0 \n" +
                        " |     /||\n" +
                        " |     /  \n" +
                        " |        \n" +
                        " |        \n" +
                        "________  \n",
                "_______+\n " +
                        " |      | \n" +
                        " |      0 \n" +
                        " |     /||\n" +
                        " |     /| \n" +
                        " |        \n" +
                        " |        \n" +
                        "________  \n"
        );

        if (mistakesCount >= 0 && mistakesCount < hangman.size()) {
            out.println(hangman.get(mistakesCount));
        }
    }

    private boolean isCorrectGuess(String secretWord, char guess) {
        return secretWord.toLowerCase().contains(String.valueOf(guess));
    }

    private boolean isWin(String maskedWord, String secretWord) {
        return maskedWord.equals(secretWord);
    }

    private boolean isLose() {
        return mistakesCount >= MAX_MISTAKES;
    }
}