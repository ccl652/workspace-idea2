package com.jk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("spring-dubbo-consumer.xml")
public class SpringbootBookConsumersApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBookConsumersApplication.class, args);
    }

}
