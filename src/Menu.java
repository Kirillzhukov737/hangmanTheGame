import service.Player;
import service.Dictionary;
import service.FileLoader;
import service.FileWriter;
import service.Game;
import service.Dialog;

import java.io.IOException;

import static java.lang.System.*;

public class Menu {

    private static final int START_GAME = 1;
    private static final int ADD_WORD = 2;
    private static final int READ_WORDS = 3;
    private static final int QUIT_GAME = 4;

    public static final String FILE_PATH = "./resources/words.txt";

    private void showMenu() {
        out.println("Выберите дейсвтие: ");
        out.println(START_GAME + "-> Игра ");
        out.println(ADD_WORD + "-> Добавить слово в словарь ");
        out.println(READ_WORDS + "-> Посмотреть список слов ");
        out.println(QUIT_GAME + "-> Выход из игры ");
    }

    public void select() {

        FileLoader fileLoader = new FileLoader(FILE_PATH);
        FileWriter fileWriter = new FileWriter(FILE_PATH);

        try {
            fileLoader.readWordsFromFile();
        } catch (IOException e) {
            out.println("Error reading file" + e);
            out.println("Завершение работы ");
            System.exit(1);
        }

        Dictionary dictionary = new Dictionary(fileLoader.getWordsList(), fileWriter);
        Game game = new Game(dictionary, fileLoader);

        while (true) {

            showMenu();

            int choise = Dialog.inputInt("Сделайте выбор: ",
                    "Неверный ввод. Введите число от 1 до 4.",
                    1,
                    4);

            switch (choise) {
                case START_GAME:
                    out.println("Игра, выберите героя: ");
                    Player selectedPlayer = Player.choisePlayer();
                    game.startGame();
                    break;
                case ADD_WORD:
                    out.println("Добавить слово");
                    dictionary.addWord();
                    break;
                case READ_WORDS:
                    out.println("Посмотреть список слов");
                    out.println(fileLoader.getWordsList());
                    break;
                case QUIT_GAME:
                    out.println("Выход из игры");
                    System.exit(0);
            }
        }
    }
}