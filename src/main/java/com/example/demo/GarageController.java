package com.example.demo;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value="/garage",method=RequestMethod.POST)
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

    public Class getFallbackClass() {
        Class klass =new Class();
        return klass;
    }



    @GetMapping("/car/{id}")
    public Car findCarCharacteristicsById(@PathVariable Long id) { return garageService.findCharacteristicsById(id);}
    public List<Car> getFallbackCar() {
        List<Car> garageList = new ArrayList<>();
        garageList.add(new Car());
        return garageList;
    }


    @HystrixCommand(fallbackMethod = "getFallbackGarage",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "5"),
                    @HystrixProperty(name = "maxQueueSize", value = "10")})
    @GetMapping
    public List<Garage> allCarsInGarage(){
        return garageService.findAll();
    }

    public List<Garage> getFallbackGarage() {
        List<Garage> garageList = new ArrayList<>();
        garageList.add(new Garage());
        return garageList;
    }



    @RequestMapping(path="/addcar", method=RequestMethod.POST)
    public void CreateCar(@RequestParam(name="title") String title, @RequestParam(name="type") String type, @RequestParam(name="power") String power){
        garageService.CreateCar(title,type,power);
    }


    @GetMapping("/{id}")
    public Garage findCarById (@PathVariable("id") Long Id) {
        return garageService.findVehicleById(Id);
    }



}
