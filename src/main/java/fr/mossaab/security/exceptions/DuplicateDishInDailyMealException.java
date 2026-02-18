package fr.mossaab.security.exceptions;

public class DuplicateDishInDailyMealException extends RuntimeException {
    public DuplicateDishInDailyMealException(String s) {
        super(s);
    }
}
