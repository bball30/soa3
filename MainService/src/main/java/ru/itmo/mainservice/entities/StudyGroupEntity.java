package ru.itmo.mainservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "study_groups")
public class StudyGroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = -1)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinates_id", nullable = false, referencedColumnName = "id")
    private CoordinatesEntity coordinates;

    @Column(name = "creationDate", nullable = false)
    private java.time.LocalDate creationDate;

    @Column(name = "studentsCount", nullable = true)
    private Long studentsCount;

    @Column(name = "shouldBeExpelled", nullable = false)
    private int shouldBeExpelled;

    @Column(name = "formOfEducation", nullable = false)
    private String formOfEducation;

    @Column(name = "semesterEnum", nullable = true)
    private String semesterEnum;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "groupAdmin_id", nullable = false, referencedColumnName = "id")
    private PersonEntity groupAdmin;
}
