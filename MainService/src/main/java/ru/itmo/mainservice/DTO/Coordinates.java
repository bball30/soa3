package ru.itmo.mainservice.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Coordinates {
    private Float x;
    @NotNull(message="y must be not null")
    private Float y; //Поле не может быть null
}
