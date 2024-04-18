package com.enviro.assessment.grad001.owethupeyane.enviro365;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Main class for the Enviro365 application.
 */
@SpringBootApplication
public class Enviro365Application {

    /**
     * Main method to start the Spring Boot application.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        // Run the Spring Boot application and obtain the application context
        ConfigurableApplicationContext context = SpringApplication.run(Enviro365Application.class, args);
    }
}
