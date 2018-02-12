package com.infosupport.machinelearning.modelmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
//@EnableConfigurationProperties(StorageProperties.class)
public class ModelmanagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModelmanagementApplication.class, args);
    }

//    @Bean
//    CommandLineRunner init(StorageService storageService) {
//        return (args) -> {
//            //storageService.deleteAll();
//            storageService.init();
//        };
//    }
}
