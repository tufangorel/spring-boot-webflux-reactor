package com.example.springboot.webflux.service;

import com.example.springboot.webflux.model.Car;
import com.example.springboot.webflux.repository.CarRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getCarList() throws InterruptedException {
        return carRepository.getCarList();
    }

    public Flux<Car> getCars(){
        return carRepository.getCars();
    }

}
