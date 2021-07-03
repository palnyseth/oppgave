package no.nyseth.oppgave.oppgave2til4.service;

import no.nyseth.oppgave.oppgave2til4.model.Parking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ParkingCalculatorController {
    private ParkingCalculatorService parkingCalculatorService;
    private Parking parking;

    @GetMapping("/takst")
    public ResponseEntity<Parking> parkingCalculator(@RequestParam String parkingZone, @RequestParam String parkingStartTime, @RequestParam String parkingEndTime) {
        System.out.println("Soutousoustoustu");
        parking = ParkingCalculatorService.calculatePrice(parkingZone, parkingStartTime, parkingEndTime);
        System.out.println("sousosuosusosu");
        return ResponseEntity.ok(parking);
    }

}
