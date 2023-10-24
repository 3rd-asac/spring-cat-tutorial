package com.asac.crud;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ClientConfig {

    @Bean
    CommandLineRunner commandLineRunner(ClientRepository repository) {
        return args -> {
            Client a = new Client("a","sample1","a");
            Client b = new Client("b","sample2","b");
            repository.saveAll(List.of(a, b));
        };
    }
}
