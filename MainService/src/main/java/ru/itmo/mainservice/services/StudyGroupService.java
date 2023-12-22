package ru.itmo.mainservice.services;

import org.springframework.data.crossstore.ChangeSetPersister;
import ru.itmo.mainservice.DTO.StudyGroupBase;
import ru.itmo.mainservice.entities.StudyGroupEntity;
import ru.itmo.mainservice.exceptions.StudyGroupDoesNotExistException;
import ru.itmo.mainservice.exceptions.WrongFilterException;

import java.util.List;
import java.util.Optional;

public interface StudyGroupService {

    Optional<StudyGroupEntity> getById(Long id);

    StudyGroupEntity updateById(Long id, StudyGroupBase studyGroup) throws ChangeSetPersister.NotFoundException;

    void deleteById(Long id) throws StudyGroupDoesNotExistException;

    Optional<StudyGroupEntity> create(StudyGroupBase studyGroup);

    List<StudyGroupEntity> findAll(List<String> filters, List<String> sorts, int size, int page) throws WrongFilterException;

    // Extra functions

    public long getAllStudentCount();

    public List<StudyGroupEntity> getAllWithNameStartWith(String name);


    public int deleteAllByStudentCount(long studentCount);
}
