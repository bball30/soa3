package ru.itmo.mainservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "locations")
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "x", nullable = true, precision = 0)
    private Double x;

    @Column(name = "y", nullable = false)
    private Long y;

    @Column(name = "z", nullable = false, precision = 0)
    private Double z;
}
