package fr.mossaab.security.exceptions;

public class DuplicateDailyMealByDateException extends RuntimeException {
    public DuplicateDailyMealByDateException(String message) {
        super(message);
    }
}