package ru.itmo.mainservice.DTO.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeleteAllByStudentCountResponse {
    long countDeleted;
}
