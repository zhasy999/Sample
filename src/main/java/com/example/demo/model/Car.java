package com.example.demo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(notes = "This car name")
    private String title;

    @Column
    private String type;

    @Column
    private String power;


    public Car(String title, String type, String power) {
        this.title = title;
        this.type = type;
        this.power = power;
    }

}
