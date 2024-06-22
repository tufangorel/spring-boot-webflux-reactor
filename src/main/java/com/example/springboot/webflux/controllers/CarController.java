package com.example.springboot.webflux.controllers;


import com.example.springboot.webflux.model.Car;
import com.example.springboot.webflux.service.CarService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<Car> getCarsBlocking() throws InterruptedException {
        return carService.getCarList();
    }

    @GetMapping( value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Car> getCarsStreamNonBlocking(){
        return carService.getCars();
    }

}