package ru.itmo.mainservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.crossstore.ChangeSetPersister;

@Getter
@AllArgsConstructor
public class StudyGroupDoesNotExistException extends ChangeSetPersister.NotFoundException {
    final Long id;
}
