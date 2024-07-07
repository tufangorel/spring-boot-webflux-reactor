package com.example.springboot.webflux.router;

import com.example.springboot.webflux.handler.CarHandler;
import com.example.springboot.webflux.handler.CarStreamHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class CarRouter {

    private final CarHandler carHandler;
    private final CarStreamHandler carsStreamHandler;

    public CarRouter(CarHandler carHandler, CarStreamHandler carsStream) {
        this.carHandler = carHandler;
        this.carsStreamHandler = carsStream;
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {

        return RouterFunctions.route()
                .GET("/router/cars", carHandler::loadCars)
                .GET("/router/cars/stream", carsStreamHandler::getCars)
                .GET("/router/cars/{id}", carHandler::loadCarById)
                .POST("/router/cars/save", carHandler::save)
                .build();
    }
}
