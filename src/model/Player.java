package model;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

public class Player {

    public Player(String selectedPlayer) {
    }

    public static Player choisePlayer() {

        final List<String> playersNames = List.of(
                "Хан Соло",
                "Оби-Ван Кеноби",
                "Энакин Скайвокер",
                "Магистр Йода",
                "Джабба Хатт",
                "Дарт Вейдер",
                "Люк Скайвокер",
                "Принцесса Лея",
                "Бобба Фет",
                "Джа-Джа Бинкс",
                "Чубакка");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            out.println("Выберите игрока: ");

            for (int i = 0; i < playersNames.size(); i++) {
                out.println((i + 1) + " " + playersNames.get(i));
            }
            out.println("Введите номер игрока: ");

            if (scanner.hasNextInt()) {

                int getChoise = scanner.nextInt();

                if (getChoise >= 1 && getChoise <= playersNames.size()) {
                    String selectedPlayer = playersNames.get(getChoise - 1);

                    out.println("Вы выбрали игрока - ваш игрок " + selectedPlayer);
                    return new Player(selectedPlayer);

                } else {
                    out.println("Такого игрока нет, попробуйте снова");
                }

            } else out.println("Выберите игрока");
            scanner.next();
        }
    }
}