package no.nyseth.oppgave.oppgave2til4.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParkingCalculatorConfig {

    /*
    Fills the ArrayList with zones, fixes the issue of "zone not found" for API calls.
    Does however not fix the issue of "zone not found" for internal/test calls, hence why the lists is being filled in the methods in calculation class.
     */
    @Bean
    CommandLineRunner commandLineRunner(ParkingCalculatorService parkingCalculatorService) {
        return args -> {
            parkingCalculatorService.parkingZoneList.add("M1");
            parkingCalculatorService.parkingZoneList.add("M2");
            parkingCalculatorService.parkingZoneList.add("M3");
        };
    }
}
