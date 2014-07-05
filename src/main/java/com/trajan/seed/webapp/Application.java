package com.trajan.seed.webapp;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Tomas Trajan
 * @creaded 2014-07-05
 */

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories
@ComponentScan("com.trajan.seed.webapp")
public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .showBanner(false)
                .run(args);
    }

}