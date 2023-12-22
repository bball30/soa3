package ru.itmo.mainservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "persons", schema = "", catalog = "")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "passportID", nullable = false)
    private String passportID;

    @Column(name = "nationality", nullable = true)
    private String nationality;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", nullable = false, referencedColumnName = "id")
    private LocationEntity location;
}
