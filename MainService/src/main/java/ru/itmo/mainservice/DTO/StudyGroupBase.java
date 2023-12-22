package ru.itmo.mainservice.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class StudyGroupBase {
    @NotNull
    @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters")
    @Pattern(regexp = "[a-zA-ZА-Яа-яёЁ0-9]*", message = "Study group name must contain only english and russian characters, and also digits")
    private String name; //Поле не может быть null, Строка не может быть пустой
    @Valid
    @NotNull(message = "coordinates must be not null")
    private Coordinates coordinates; //Поле не может быть null
    @Min(value = 1, message = "studentsCount must be greater than 0")
    @NotNull(message = "studentsCount must be not null")
    private long studentsCount; //Значение поля должно быть больше 0
    @NotNull(message = "shouldBeExpelled must be not null")
    @Min(value = 1, message = "shouldBeExpelled must be greater than 0")
    @Max(value = 2147483647, message = "shouldBeExpelled must be less than 2147483647")
    private Integer shouldBeExpelled; //Значение поля должно быть больше 0, Поле не может быть null
    @NotNull(message = "formOfEducation must be not null")
    @Pattern(regexp = "DISTANCE_EDUCATION|FULL_TIME_EDUCATION|EVENING_CLASSES", message = "formOfEducation must be one of DISTANCE_EDUCATION, FULL_TIME_EDUCATION or EVENING_CLASSES")
    private String formOfEducation; //Поле не может быть null
    @NotNull(message = "semesterEnum must be not null")
    @Pattern(regexp = "FOURTH|FIFTH|SIXTH", message = "semesterEnum must be one of FOURTH, FIFTH or SIXTH")
    private String semesterEnum; //Поле может быть null
    @NotNull
    @Valid
    private Person groupAdmin; //Поле не может быть null

}
