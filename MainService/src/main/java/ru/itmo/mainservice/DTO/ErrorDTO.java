package ru.itmo.mainservice.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorDTO {
    private String message;
    private LocalDateTime dateTime;

    public ErrorDTO(String message) {
        this.message = message;
        dateTime = LocalDateTime.now();
    }
}
