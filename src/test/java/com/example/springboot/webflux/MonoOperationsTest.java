package com.example.springboot.webflux;


import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

class MonoOperationsTest {

    @Test
    void testMonoOnComplete() {

        Mono<String> monoString = Mono.just("monoString").log();
        monoString.subscribe(System.out::println);
    }

    @Test
    void testMonoOnError() {

        Mono<?> monoString = Mono.just("monoString")
                .then( Mono.error( new RuntimeException("Exception occured")) )
                .log();

        monoString.subscribe( System.out::println, e -> System.out.println(e.getMessage()) );


    }
}
