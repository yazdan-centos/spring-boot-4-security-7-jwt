package fr.mossaab.security.exceptions;

public class ServiceException extends Throwable {
    public ServiceException(String failedToUpdateApplicationSettings, Exception e) {
        super(failedToUpdateApplicationSettings, e);
    }
}
