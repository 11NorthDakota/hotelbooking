package by.northdakota.booking_backend.Exception;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class AppError{

    private int status;
    private String message;
    private Timestamp timestamp;

    public AppError(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

}
