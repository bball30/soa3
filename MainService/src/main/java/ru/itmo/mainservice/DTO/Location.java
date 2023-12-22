package ru.itmo.mainservice.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Location {
    private Double x;
    @NotNull(message = "y must be not null")
    private Long y; //Поле не может быть null
    @NotNull(message = "z must be not null")
    private Double z; //Поле не может быть null
}
