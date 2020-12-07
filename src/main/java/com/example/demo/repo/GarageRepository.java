package com.example.demo.repo;

import com.example.demo.model.Garage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GarageRepository extends CrudRepository<Garage, Long> {

    Garage findCarById(Long id);

    List<Garage> findAll();
}
