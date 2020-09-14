package com.prueba.apache.kafka.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sonia
 */
@RestController
public class HomeRest {


    @RequestMapping("/")
    public String home() {
        return "Hello Spring Boot with Docker";
    }

}
