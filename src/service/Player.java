package service;

import java.util.List;

import static java.lang.System.out;

public class Player {

    private String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
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

        out.println("Выберите героя: ");

        for (int i = 0; i < playersNames.size(); i++) {
            out.printf("%d %s \n", (i + 1), playersNames.get(i));
        }

        int heroNumber = Dialog.inputInt("Введите номер героя",
                "Вам нужно выбрать номер от 1 до 11 ",
                1, playersNames.size());

        String selectedHero = playersNames.get(heroNumber - 1);
        out.println("Вы выбрали героя - ваш герой " + heroNumber);
        return new Player(selectedHero);
    }

}