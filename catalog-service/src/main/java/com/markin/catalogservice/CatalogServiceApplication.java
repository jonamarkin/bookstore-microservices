package com.markin.catalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class CatalogServiceApplication {

    public static void main(String[] args) {
        System.out.println("something");
        SpringApplication.run(CatalogServiceApplication.class, args);

    }

}
