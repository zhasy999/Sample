package com.example.demo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "Garage")
public class Garage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long carId;

    @Column
    private Long classId;

    @Column
    private Long brandId;

    public Garage(Long carId, Long classId, Long brandId) {
        this.carId = carId;
        this.classId = classId;
        this.brandId = brandId;
    }


}
