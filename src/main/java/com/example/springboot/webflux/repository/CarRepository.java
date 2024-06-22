package com.example.springboot.webflux.repository;


import com.example.springboot.webflux.model.Car;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CarRepository {


    public List<Car> getCarList() throws InterruptedException {

        List<Car> carList = new ArrayList<>();
        for( int i = 0; i<10; i++ ) {
            Car car = new Car();
            car.setId(i);
            car.setType("type"+i);
            System.out.println("Current car : "+car);
            Thread.sleep(1000);
            carList.add(car);
        }
        return carList;
    }

    public Flux<Car> getCars(){

        return Flux.range(1,10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext( i -> System.out.println("element id : "+i) )
                .map( i -> new Car(i, "type "+i) );
    }

}