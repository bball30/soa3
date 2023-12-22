package ru.itmo.mainservice.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.mainservice.DTO.StudyGroupBase;
import ru.itmo.mainservice.DTO.responses.DeleteAllByStudentCountResponse;
import ru.itmo.mainservice.DTO.responses.GetAllStudentsCountResponse;
import ru.itmo.mainservice.entities.StudyGroupEntity;
import ru.itmo.mainservice.exceptions.StudyGroupDoesNotExistException;
import ru.itmo.mainservice.exceptions.WrongFilterException;
import ru.itmo.mainservice.services.StudyGroupService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/study-groups")
public class StudyGroupsController {

    private final StudyGroupService studyGroupService;

    @Autowired
    public StudyGroupsController(StudyGroupService studyGroupService) {
        this.studyGroupService = studyGroupService;
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("PONG");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> GetStudyGroup(@PathVariable(name = "id") long id)
            throws StudyGroupDoesNotExistException {
        Optional<StudyGroupEntity> maybeStudyGroup = studyGroupService.getById(id);
        if (maybeStudyGroup.isEmpty()) {
            throw new StudyGroupDoesNotExistException(id);
        }
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(maybeStudyGroup.get());
    }

    @GetMapping("")
    public ResponseEntity<?> getStudyGroups(
            @RequestParam(name = "filter", required = false) @DefaultValue("") List<String> filters,
            @RequestParam(name = "sort", required = false) @DefaultValue("") List<String> sorts,
            @RequestParam(name = "page", defaultValue = "1") @Valid @Positive(message = "page must be positive") Integer page,
            @RequestParam(name = "size", defaultValue = "5") @Valid @Positive(message = "size must be positive") Integer size
    ) throws WrongFilterException {
        List<StudyGroupEntity> studyGroups = studyGroupService.findAll(filters, sorts, page, size);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(studyGroups);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudyGroup(
            @PathVariable(name = "id") @Valid @Positive(message = "id must be positive") long id)
            throws StudyGroupDoesNotExistException {
        studyGroupService.deleteById(id);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("successful");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudyGroup(
            @PathVariable(name = "id") @Valid @Positive(message = "id must be positive") long id,
            @RequestBody @NotNull(message = "Study group must not be null") @Valid StudyGroupBase studyGroup)
            throws ChangeSetPersister.NotFoundException {
        StudyGroupEntity updatedStudyGroup = studyGroupService.updateById(id, studyGroup);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(updatedStudyGroup);
    }
    
    @PostMapping("")
    public ResponseEntity<?> createStudyGroup(@RequestBody @NotNull(message = "Study group must not be null") StudyGroupBase studyGroup) {
        Optional<StudyGroupEntity> maybeCreatedStudyGroup = studyGroupService.create(studyGroup);
        if (maybeCreatedStudyGroup.isEmpty()) {
            return ResponseEntity.badRequest().body("Study group error");
        }
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("successful");
    }

    @GetMapping("/all-students-count")
    public ResponseEntity<?> getAllStudentsCount() {
        long count = studyGroupService.getAllStudentCount();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(new GetAllStudentsCountResponse(count));
    }

    @GetMapping("/start-with")
    public ResponseEntity<?> getAllWithNameStartWith(@NotNull(message = "name must not be null") @RequestParam("name") String name) {
        List<StudyGroupEntity> studyGroups = studyGroupService.getAllWithNameStartWith(name);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(studyGroups);
    }

    @DeleteMapping("/student-count/{studentCount}")
    public ResponseEntity<?> deleteAllByStudentCount(@NotNull(message = "studentCount must not be null") @PathVariable(name = "studentCount") Long studentCount) {
        int countDeleted = studyGroupService.deleteAllByStudentCount(studentCount);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(new DeleteAllByStudentCountResponse(countDeleted));
    }
}
