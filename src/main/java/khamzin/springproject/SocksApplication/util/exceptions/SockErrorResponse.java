package khamzin.springproject.SocksApplication.util.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class SockErrorResponse {
    private HttpStatus HttpStatus;
    private String name;
    private String date;
}
