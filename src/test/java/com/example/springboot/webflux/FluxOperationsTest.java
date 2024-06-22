package com.example.springboot.webflux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

class FluxOperationsTest {

    @Test
    void testFluxOnComplete() {

        Flux<String> stringFlux = Flux.just("one", "two", "three", "four").log();

        stringFlux.subscribe( System.out::println );
    }

    @Test
    void testFluxOnError() {

        Flux<String> stringFlux = Flux.just("one", "two", "three", "four")
                .concatWith( Flux.error( new RuntimeException("Exception occured")) )
                .log();

        stringFlux.subscribe( System.out::println, e-> System.out.println(e.getMessage()) );
    }
}
