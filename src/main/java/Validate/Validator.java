package Validate;

/**
 * This class is used for validate UI
 *
 * @author Nguyễn Duy Phúc
 */
public class Validator {

    /**
     * Validate input String
     *
     * @param pattern Pattern want to check
     * @param input input want to check
     * @return result of validate
     */
    public static boolean inputString(String pattern, String input) {
        boolean wrongFormat;
        //validate
        wrongFormat = (!input.matches(pattern));
        return wrongFormat;
    }

}
