package com.example.demo.controller;

import com.example.demo.service.GarageService;
import com.example.demo.model.Brand;
import com.example.demo.model.Car;
import com.example.demo.model.Class;
import com.example.demo.model.Garage;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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




    @HystrixCommand(fallbackMethod = "getFallbackBrand")
    @GetMapping("/brand/{id}")
    public void findCarBrandById(@PathVariable Long id) { garageService.findBrandById(id);}

    public void getFallbackBrand(Long id){
        System.out.println("Brand service fallen");
    }

    @HystrixCommand(fallbackMethod = "getFallbackClass")
    @GetMapping("/class/{id}")
    public ResponseEntity<Class>  findCarClassById(@PathVariable Long id) { return garageService.findClassById(id);}
    public ResponseEntity<Class> getFallbackClass(Long id){
        return new ResponseEntity<Class>(new Class("error"),HttpStatus.BAD_REQUEST);
    }

    @HystrixCommand(fallbackMethod = "getFallbackCar")
    @GetMapping("/car/{id}")
    public ResponseEntity<Car> findCarCharacteristicsById(@PathVariable Long id) { return garageService.findCharacteristicsById(id);}
    public ResponseEntity<Car> getFallbackCar(Long id){
        return new ResponseEntity<Car>(new Car("error","error","error"),HttpStatus.BAD_REQUEST);
    }


    @HystrixCommand(fallbackMethod = "getFallbackGarage")
    @GetMapping
    public List<String> allCarsInGarage(){
        return garageService.findAll();
    }

    public List<String> getFallbackGarage() {
        List<String> garageList = new ArrayList<>();
        garageList.add("error 404");
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
