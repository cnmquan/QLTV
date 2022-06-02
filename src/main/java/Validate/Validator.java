package Validate;

import java.util.Scanner;

public class Validator {

    public static String inputName(Scanner sc, String prompt, String pattern) {
        boolean wrongFormat = false;
        String name = "";
        do {
            System.out.print(prompt);

            name = sc.nextLine();
            wrongFormat = (!name.matches(pattern));
        } while (wrongFormat);
        return name;
    }

    public static boolean inputString(String pattern, String input) {
        boolean wrongFormat = false;
        wrongFormat = (!input.matches(pattern));
        return wrongFormat;
    }

    public static String inputDate(Scanner sc, String prompt, String pattern) {
        boolean wrongFormat = false;
        String s = "";
        do {
            System.out.print(prompt);

            s = sc.nextLine();
            wrongFormat = (!s.matches(pattern));
        } while (wrongFormat);
        return s;
    }

}
