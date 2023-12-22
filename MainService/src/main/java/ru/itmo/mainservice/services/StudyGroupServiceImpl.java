package ru.itmo.mainservice.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import ru.itmo.mainservice.DTO.Filter;
import ru.itmo.mainservice.DTO.StudyGroupBase;
import ru.itmo.mainservice.entities.CoordinatesEntity;
import ru.itmo.mainservice.entities.LocationEntity;
import ru.itmo.mainservice.entities.PersonEntity;
import ru.itmo.mainservice.entities.StudyGroupEntity;
import ru.itmo.mainservice.exceptions.StudyGroupDoesNotExistException;
import ru.itmo.mainservice.exceptions.WrongFilterException;
import ru.itmo.mainservice.repositories.StudyGroupRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static ru.itmo.mainservice.utils.Constants.STUDY_GROUP_VALIDATION_EXPRESSION;

@Service
public class StudyGroupServiceImpl implements StudyGroupService {

    private final StudyGroupRepository studyGroupRepo;

    @Autowired
    public StudyGroupServiceImpl(StudyGroupRepository studyGroupRepository) {
        studyGroupRepo = studyGroupRepository;
    }

    @Override
    @Transactional
    public Optional<StudyGroupEntity> getById(Long id) {
        return studyGroupRepo.findById(id);
    }

    @Override
    @Transactional
    public StudyGroupEntity updateById(Long id, StudyGroupBase studyGroup) throws ChangeSetPersister.NotFoundException {
        Optional<StudyGroupEntity> maybeStudyGroup = studyGroupRepo.findById(id);
        if (maybeStudyGroup.isPresent()) {
            StudyGroupEntity studyGroupEntity = this.dataToEntity(studyGroup);
            studyGroupEntity.setId(id);
            studyGroupEntity.setCreationDate(maybeStudyGroup.get().getCreationDate());
            studyGroupRepo.save(studyGroupEntity);
            return studyGroupEntity;
        }
        throw new ChangeSetPersister.NotFoundException();
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws StudyGroupDoesNotExistException {
        studyGroupRepo.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<StudyGroupEntity> create(StudyGroupBase studyGroup) {
        StudyGroupEntity studyGroupEntity = this.dataToEntity(studyGroup);

        return Optional.of(studyGroupRepo.save(studyGroupEntity));
    }

    @Transactional
    public List<StudyGroupEntity> findAll(List<String> rawFilters, List<String> sorts, int page, int size) throws WrongFilterException {

//        for (String rawFilter : rawFilters) {
//            if (!rawFilter.matches(STUDY_GROUP_VALIDATION_EXPRESSION)) {
//                throw new WrongFilterException(rawFilter);
//            }
//        }

//        List<Filter> filters = rawFilters.stream().map(filter -> {
//            String[] parts = filter.split(":");
//            return new Filter(parts[0], parts[1]);
//        }).toList();

        return studyGroupRepo.findAll();
    }


    @Override
    @Transactional
    public long getAllStudentCount(){
        return studyGroupRepo.findAll().stream().mapToLong(StudyGroupEntity::getStudentsCount).sum();
    }

    @Override
    @Transactional
    public List<StudyGroupEntity> getAllWithNameStartWith(String name) {
        return studyGroupRepo.getAllByNameStartsWith(name);
    }

    @Override
    @Transactional
    public int deleteAllByStudentCount(long studentCount) {
        return studyGroupRepo.deleteAllByStudentsCount(studentCount);
    }


    private StudyGroupEntity dataToEntity(StudyGroupBase studyGroup) {
        LocationEntity locationEntity = new LocationEntity();
        CoordinatesEntity coordinatesEntity = new CoordinatesEntity();
        PersonEntity personEntity = new PersonEntity();
        StudyGroupEntity studyGroupEntity = new StudyGroupEntity();

        personEntity.setLocation(locationEntity);
        studyGroupEntity.setGroupAdmin(personEntity);
        studyGroupEntity.setCoordinates(coordinatesEntity);

        studyGroupEntity.setName(studyGroup.getName());
        studyGroupEntity.setSemesterEnum(studyGroup.getSemesterEnum());
        studyGroupEntity.setStudentsCount(studyGroup.getStudentsCount());
        studyGroupEntity.setFormOfEducation(studyGroup.getFormOfEducation());
        studyGroupEntity.setShouldBeExpelled(studyGroup.getShouldBeExpelled());
        studyGroupEntity.setCreationDate(LocalDate.now());



        personEntity.setName(studyGroup.getGroupAdmin().getName());
        personEntity.setNationality(studyGroup.getGroupAdmin().getNationality());
        personEntity.setPassportID(studyGroup.getGroupAdmin().getPassportID());

        locationEntity.setX(studyGroup.getGroupAdmin().getLocation().getX());
        locationEntity.setY(studyGroup.getGroupAdmin().getLocation().getY());
        locationEntity.setZ(studyGroup.getGroupAdmin().getLocation().getZ());

        coordinatesEntity.setX(studyGroup.getCoordinates().getX());
        coordinatesEntity.setY(studyGroup.getCoordinates().getY());

        return studyGroupEntity;
    }
}
