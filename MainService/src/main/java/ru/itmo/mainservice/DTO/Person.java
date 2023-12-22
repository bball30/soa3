package ru.itmo.mainservice.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Person {
    @NotNull(message = "name must be not null")
    @Size(min = 1, max = 255, message = "Name length must be between 1 and 255")
    @Pattern(regexp = "[a-zA-ZА-Яа-яёЁ0-9]*", message = "Name must contain only english and russian characters, and also digits")
    private String name; //Поле не может быть null, Строка не может быть пустой
    @NotNull(message = "passportID must be not null")
    @Pattern(regexp = "[a-zA-ZА-Яа-яёЁ0-9]*", message = "passportID must contain only english and russian characters, and also digits")
    @Size(min = 7, max = 255, message = "passportID length must be between 1 and 255")
    private String passportID; //Длина строки должна быть не меньше 7, Строка не может быть пустой, Поле не может быть null
    @NotNull(message = "nationality cannot be null")
    @Pattern(regexp = "UNITED_KINGDOM|INDIA|ITALY|SOUTH_KOREA", message = "Nationality must be one of UNITED_KINGDOM, INDIA, ITALY, or SOUTH_KOREA")
    private String nationality; //Поле может быть null
    @NotNull(message = "location must be not null")
    @Valid
    private Location location; //Поле не может быть null

}
