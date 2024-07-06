package com.example.springboot.webflux.handler;


import com.example.springboot.webflux.model.Car;
import com.example.springboot.webflux.repository.CarRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CarStreamHandler {

    private final CarRepository carRepository;

    public CarStreamHandler(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Mono<ServerResponse> getCars(ServerRequest request) {

        Flux<Car> carsStream = carRepository.getCarsStream();
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(carsStream, Car.class);
    }
}
