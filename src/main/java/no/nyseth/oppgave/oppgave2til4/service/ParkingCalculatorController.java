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
    private Parking parking;

    @GetMapping("/takst")
    public ResponseEntity<?> parkingCalculator(@RequestParam String parkingZone, @RequestParam String parkingStartTime, @RequestParam String parkingEndTime) {
        //parking = ParkingCalculatorService.calculatePrice("M2", "2021-07-10T17:00:00", "2021-07-10T18:00:00");
        parking = ParkingCalculatorService.calculatePrice(parkingZone, parkingStartTime, parkingEndTime);
        System.out.println(parking.getParkingFee());
        return ResponseEntity.ok(parking);
    }

}
