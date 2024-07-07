package com.example.springboot.webflux.controllers;


import com.example.springboot.webflux.model.Car;
import com.example.springboot.webflux.service.CarService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;


@WebFluxTest(CarController.class)
class CarControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private CarService carService;

    @Test
    void getCarsBlockingTest() throws InterruptedException {

        Car car1 = new Car(1,"type1");
        Car car2 = new Car(2,"type2");

        List<Car> cars = new ArrayList<>();
        cars.add(car1); cars.add(car2);

        Mockito.when(carService.getCarList()).thenReturn(cars);

        webTestClient.get().uri("/cars")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    void getCarsStreamNonBlockingTest() {

        Car car1 = new Car(1,"type1");
        Car car2 = new Car(2,"type2");

        Flux<Car> carFlux = Flux.just( car1, car2 );
        Mockito.when(carService.getCars()).thenReturn(carFlux);

        Flux<Car> responseBody = webTestClient.get().uri("/cars")
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(Car.class)
                .getResponseBody();

        StepVerifier.create( responseBody )
                .expectSubscription()
                .verifyComplete();

    }
}
