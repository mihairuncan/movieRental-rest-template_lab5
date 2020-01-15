package ro.ubb.movieRental.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
@ComponentScan({"ro.ubb.movieRental.client.service",
        "ro.ubb.movieRental.client.ui"})
public class ClientConfig {
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
