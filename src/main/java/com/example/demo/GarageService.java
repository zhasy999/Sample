package com.example.demo;

import com.example.demo.model.Brand;
import com.example.demo.model.Car;
import com.example.demo.model.Class;
import com.example.demo.model.Garage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class GarageService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private GarageRepository garageRepo;


    @Transactional
    public boolean addCar(Garage garage) {
        if (garageRepo.findCarById(garage.getId())!=null) {
            garage.setId(Long.MIN_VALUE);
            System.out.println("Error");
            return false;
        }
        garageRepo.save(garage);
        return true;
    }

    @Transactional
    public Garage findVehicleById(Long id) {
        return garageRepo.findCarById(id);
    }

    @Transactional
    public List<Garage> findAll() {
        List<Garage> garage = garageRepo.findAll();
        return garage;
    }

    @Transactional
    public Brand findBrandById(Long id) {
        return restTemplate.getForObject(
                "http://localhost:8084/brand/" + id,
                Brand.class);
    }
    @Transactional
    public Class findClassById(Long id) {
        return restTemplate.getForObject(
                "http://localhost:8083/class/" + id,
                Class.class);
    }
    @Transactional
    public Car findCharacteristicsById(Long id) {
        return restTemplate.getForObject(
                "http://localhost:8082/car/" + id,
                Car.class);
    }
    @Transactional
    public void CreateCar(String title,String type,String power) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = "http://localhost:8082/car/create?title="+title+"&type="+type+"&power=" + power;;

        Car car=new Car(title,type,power);

        restTemplate.postForObject(url,car,Car.class);

    }


}

