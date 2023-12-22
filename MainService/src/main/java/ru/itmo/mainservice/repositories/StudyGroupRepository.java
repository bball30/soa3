package ru.itmo.mainservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.mainservice.entities.StudyGroupEntity;

import java.util.List;

@Repository
public interface StudyGroupRepository extends JpaRepository<StudyGroupEntity, Long> {

    void deleteById(Long id);

    List<StudyGroupEntity> getAllByNameStartsWith(String name);

    int deleteAllByStudentsCount(Long studentCount);

}
