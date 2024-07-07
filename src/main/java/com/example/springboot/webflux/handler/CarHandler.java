package com.example.springboot.webflux.handler;


import com.example.springboot.webflux.model.Car;
import com.example.springboot.webflux.repository.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CarHandler {

    private final CarRepository carRepository;

    public CarHandler(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Mono<ServerResponse> loadCars(ServerRequest request) {

        Flux<Car> carListNonBlocking = carRepository.getCarListNonBlocking();
        return ServerResponse.ok().body(carListNonBlocking, Car.class);
    }

    public Mono<ServerResponse> loadCarById(ServerRequest request) {

        int id = Integer.parseInt( request.pathVariable("id"));

        Mono<Car> car = carRepository.getCarListNonBlocking()
                                        .filter(c -> c.getId() == id )
                                        .next();
        return ServerResponse.ok().body(car, Car.class);
    }

    public Mono<ServerResponse> save(ServerRequest request) {

        Mono<Car> carMono = request.bodyToMono(Car.class);
        Mono<String> stringMono = carMono.map(dto -> dto.getId() + ":" + dto.getType());

        return ServerResponse.ok().body(stringMono, String.class);
    }
}
