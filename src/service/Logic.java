package service;

public class Logic {
    public static Boolean isInt(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException numberFormat) {
            return false;
        }
    }
}