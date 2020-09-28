package com.example.demo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "cars")
public class Garage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long carId;
    private Long classId;
    private Long brandId;

    public Garage(Long carId, Long classId, Long brandId) {
        this.carId = carId;
        this.classId = classId;
        this.brandId = brandId;
    }


}
