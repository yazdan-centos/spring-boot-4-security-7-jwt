package fr.mossaab.security.exceptions;

public class PersonnelAlreadyExistsException extends RuntimeException {
    public PersonnelAlreadyExistsException(String message) {
        super(message);
    }
}