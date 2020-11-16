package com.example.demo;

import com.example.demo.model.Brand;
import com.example.demo.model.Car;
import com.example.demo.model.Class;
import com.example.demo.model.Garage;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value="/api/garage",method=RequestMethod.POST)
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
