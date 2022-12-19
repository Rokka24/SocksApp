package khamzin.springproject.SocksApplication.util;

import lombok.Data;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Data
public class ErrorsUtil {
    public static void returnErrorsToClient(BindingResult bindingResult) {
        StringBuilder errorResponse = new StringBuilder();

        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            errorResponse.append(error.getField())
                    .append(" - ")
                    .append(error.getDefaultMessage() == null ? error.getCode() : error.getDefaultMessage())
                    .append(";");
        }
        throw new SocksException(errorResponse.toString());
    }
}
