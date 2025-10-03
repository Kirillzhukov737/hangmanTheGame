package service;

import java.util.Scanner;

import static java.lang.System.*;

public final class Dialog {

    private static final Scanner SCANNER = new Scanner(in);

    private Dialog() {
    }

    public static int inputInt(String title, String failMessage, int min, int max) {

        while (true) {
            out.println(title);
            String s = SCANNER.nextLine().trim();

            if (isNumber(s)) {
                int number = Integer.parseInt(s);
                if (number >= min && number <= max) {
                    return number;
                }
            }
            out.println(failMessage);
        }
    }

    public static String inputString(String title, String failMessage, String regex) {

        while (true) {
            out.println(title);
            String s = SCANNER.nextLine().trim().toLowerCase();

            if (s.matches(regex)) {
                return s;
            }
            out.println(failMessage);
        }
    }

    private static boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}