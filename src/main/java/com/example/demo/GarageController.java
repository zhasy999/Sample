package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/garage")
public class GarageController {

    @Autowired
    private GarageService garageService;

    @PostMapping("/create")
    public String addCar(@RequestParam Long carId,@RequestParam Long classId,@RequestParam Long brandId) {
        Garage garage = new Garage();
        garage.setCarId(carId);
        garage.setClassId(classId);
        garage.setBrandId(brandId);

        if (garageService.addCar(garage)) {
            return ("Car " + garage + " added");
        }
        return (garage + " Already exist");
    }

    @GetMapping("/{id}/brand")
    public Brand findCarBrandById(@PathVariable("id") Long Id) { return garageService.findBrandById(Id);}

    @GetMapping("/{id}/class")
    public Class findCarClassById(@PathVariable("id") Long Id) { return garageService.findClassById(Id);}

    @GetMapping("/{id}/car")
    public Car findCarCharacteristicsById(@PathVariable("id") Long Id) { return garageService.findCharacteristicsById(Id);}

    @GetMapping
    public List<Garage> allCarsInGarage(){
        return garageService.findAll();
    }

    @GetMapping("/{id}")
    public Garage findCarById (@PathVariable("id") Long Id) {
        return garageService.findVehicleById(Id);
    }

}
