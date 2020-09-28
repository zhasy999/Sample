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

    @GetMapping("/brand/{id}")
    public Brand findCarBrandById(@PathVariable Long id) { return garageService.findBrandById(id);}

    @GetMapping("/class/{id}")
    public Class findCarClassById(@PathVariable Long id) { return garageService.findClassById(id);}

    @GetMapping("/car/{id}")
    public Car findCarCharacteristicsById(@PathVariable Long id) { return garageService.findCharacteristicsById(id);}

    @GetMapping
    public List<Garage> allCarsInGarage(){
        return garageService.findAll();
    }

    @GetMapping("/{id}")
    public Garage findCarById (@PathVariable("id") Long Id) {
        return garageService.findVehicleById(Id);
    }

}
