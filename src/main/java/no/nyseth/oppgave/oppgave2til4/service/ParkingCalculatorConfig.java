package no.nyseth.oppgave.oppgave2til4.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParkingCalculatorConfig {

    @Bean
    CommandLineRunner commandLineRunner(ParkingCalculatorService parkingCalculatorService) {
        return args -> {
            parkingCalculatorService.parkingZoneList.add("M1");
            parkingCalculatorService.parkingZoneList.add("M2");
            parkingCalculatorService.parkingZoneList.add("M3");
        };
    }
}
