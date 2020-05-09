package sample;

public class Formatting {

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public static boolean isNonNegativeNumeric(String str) {
        return str.matches("\\d+(\\.\\d+)?");
    }

    public static boolean isInteger(String str) {
        return str.matches("\\d+");
    }
}
