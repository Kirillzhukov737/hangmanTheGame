import model.Player;
import service.DictionaryService;
import service.FileLoaderService;
import service.FileWriterService;
import service.GameService;

import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.*;

public class Menu {

    private final Scanner scanner = new Scanner(in);

    private void showMenu() {

        out.println("Выберите действие: \n" +
                "1 -> Игра против бота \n" +
                "2 -> Добавить слово в словарь \n" +
                "3 -> Посмотреть список слов \n" +
                "4 -> Выход из игры");
    }

    public void menuSelector() {

        FileLoaderService fileLoaderService = new FileLoaderService();
        FileWriterService fileWriterService = new FileWriterService();

        try {
            fileLoaderService.readWordsFromFile();
        } catch (IOException e) {
            out.println("Ошибка при чтении файла");
        }

        DictionaryService dictionaryService = new DictionaryService(fileLoaderService.getWordsList(), fileWriterService);
        GameService gameService = new GameService(dictionaryService, fileLoaderService, scanner);

        while (true) {

            showMenu();
            out.println("Сделайте выбор:  ");

            String input = scanner.nextLine().trim();
            int choise;
            try {
                choise = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                continue;
            }

            switch (choise) {
                case 1:
                    out.println("Игра против бота, выберите героя: ");
                    Player selectedPlayer = Player.choisePlayer();
                    out.println("Герой выбран " + selectedPlayer);
                    gameService.startGame();
                    break;
                case 2:
                    out.println("Добавить слово");
                    dictionaryService.addWord();
                    break;
                case 3:
                    out.println("Посмотреть список слов");
                    out.println(fileLoaderService.getWordsList());
                    break;
                case 4:
                    out.println("Выход из игры");
                    System.exit(0);
            }
        }
    }
}