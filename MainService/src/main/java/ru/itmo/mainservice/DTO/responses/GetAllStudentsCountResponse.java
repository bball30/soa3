package ru.itmo.mainservice.DTO.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllStudentsCountResponse {
    long allStudentCount;
}
