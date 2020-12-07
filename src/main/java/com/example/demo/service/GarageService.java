package com.example.demo.service;

import com.example.demo.Producer;
import com.example.demo.model.Car;
import com.example.demo.model.Class;
import com.example.demo.model.Garage;
import com.example.demo.repo.GarageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class GarageService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private GarageRepository garageRepo;

    private Producer producer;

    public GarageService(Producer producer) {
        this.producer = producer;
    }


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
    public List<String> findAll() {
        List<Garage> garage = garageRepo.findAll();
        ArrayList<String> str = new ArrayList<String>();
        for(Garage g : garage){
            str.add("Garage Id: " + g.getId() + "  carId: " + g.getCarId() + "  brandId: " + g.getBrandId() + "  classId: " + g.getClassId() + " ");
        }
        return str;
    }



    @Transactional
    public void findBrandById(Long id) {
        this.producer.movieRequestNotify("Request sended");

//        String apiCredentials = "rest-client:p@ssword";
//        String base64Credentials = new String(Base64.getEncoder().encodeToString(apiCredentials.getBytes()));
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", "Basic " + base64Credentials);
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//        return restTemplate.exchange("http://localhost:8084/brand/" + id, HttpMethod.GET, entity, Brand.class);
    }

    @Transactional
    public ResponseEntity<Class>  findClassById(Long id) {
        String apiCredentials = "rest-client:p@ssword";
        String base64Credentials = new String(Base64.getEncoder().encodeToString(apiCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange("http://localhost:8083/class/" + id, HttpMethod.GET, entity, Class.class);
    }
    @Transactional
    public ResponseEntity<Car> findCharacteristicsById(Long id) {
        String apiCredentials = "rest-client:p@ssword";
        String base64Credentials = new String(Base64.getEncoder().encodeToString(apiCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange("http://localhost:8082/car/" + id, HttpMethod.GET, entity, Car.class);
    }

    @Transactional
    public void CreateCar(String title,String type,String power) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = "http://localhost:8082/car/create?title="+title+"&type="+type+"&power=" + power;

        Car car=new Car(title,type,power);

        restTemplate.postForObject(url,car,Car.class);

    }


}

