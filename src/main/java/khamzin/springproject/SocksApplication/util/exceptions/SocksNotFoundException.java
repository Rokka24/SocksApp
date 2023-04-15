package khamzin.springproject.SocksApplication.util.exceptions;

public class SocksNotFoundException extends RuntimeException {
    public SocksNotFoundException(String message) {
        super(message);
    }
}
