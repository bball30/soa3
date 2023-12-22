package ru.itmo.mainservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "coordinates")
public class CoordinatesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "x", nullable = true)
    private Float x;

    @Column(name = "y", nullable = false)
    private Float y;

}
