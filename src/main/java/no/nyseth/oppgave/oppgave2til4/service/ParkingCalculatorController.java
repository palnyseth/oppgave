package no.nyseth.oppgave.oppgave2til4.service;

import no.nyseth.oppgave.oppgave2til4.model.Parking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class ParkingCalculatorController {
    private Parking parking;

    @GetMapping("/takst")
    public ResponseEntity<Parking> parkingCalculator(@RequestParam String parkingZone, @RequestParam String parkingStartTime, @RequestParam String parkingEndTime) {
        parking = ParkingCalculatorService.calculatePrice(parkingZone, parkingStartTime, parkingEndTime);
        return ResponseEntity.ok(parking);
    }
}
