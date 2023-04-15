package khamzin.springproject.SocksApplication.util.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@ControllerAdvice
public class SockExceptionHandler {


    @ExceptionHandler(SocksNotFoundException.class)
    public ResponseEntity<SockErrorResponse> handleException(SocksNotFoundException e) {
        log.info("SockNotFound exception handler starts");
        SockErrorResponse errorResponse = new SockErrorResponse(HttpStatus.NOT_FOUND, e.getMessage(),
                new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(new Date()));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SocksException.class)
    public ResponseEntity<SockErrorResponse> handleException(SocksException e) {
        log.info("Sock exception handler starts");
        SockErrorResponse errorResponse = new SockErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage(),
                new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(new Date()));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
