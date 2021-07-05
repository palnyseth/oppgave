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

    /**
     * Endpoint in Controller for parking fee calculator
     * @param parkingZone - Zone
     * @param parkingStartTime - StartTime
     * @param parkingEndTime - EndTime
     * Creates an Parking object and passes the returned object from the calculation to it
     * @return - the object and returns it in JSON form.
     */
    @GetMapping("/takst")
    public ResponseEntity<Parking> parkingCalculator(@RequestParam String parkingZone, @RequestParam String parkingStartTime, @RequestParam String parkingEndTime) {
        parking = ParkingCalculatorService.calculatePrice(parkingZone, parkingStartTime, parkingEndTime);
        return ResponseEntity.ok(parking);
    }
}
