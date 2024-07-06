package com.example.springboot.webflux.router;

import com.example.springboot.webflux.handler.CarHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class CarRouter {

    private final CarHandler carHandler;

    public CarRouter(CarHandler carHandler) {
        this.carHandler = carHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {

        return RouterFunctions.route()
                .GET("/router/cars", carHandler::loadCars).build();
    }
}
