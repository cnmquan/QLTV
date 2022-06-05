package Validate;

public class Validator {

    public static boolean inputString(String pattern, String input) {
        boolean wrongFormat ;
        wrongFormat = (!input.matches(pattern));
        return wrongFormat;
    }

}
