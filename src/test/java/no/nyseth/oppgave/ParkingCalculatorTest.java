package no.nyseth.oppgave;

import no.nyseth.oppgave.oppgave1.ParkingCalculatorOppg1;
import no.nyseth.oppgave.oppgave2til4.model.Parking;
import no.nyseth.oppgave.oppgave2til4.service.ParkingCalculatorConfig;
import no.nyseth.oppgave.oppgave2til4.service.ParkingCalculatorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ParkingCalculatorTest {
    //
    // Oppgave 1 Calculator
    // Tests, zone M1
    //
    @Test
    void testCorrectValueOppg1() {
        Assertions.assertEquals(30, ParkingCalculatorOppg1.calculatePrice("M1", "2021-07-13T17:00:00.000", "2021-07-13T17:30:00.000"));
        Assertions.assertEquals(60, ParkingCalculatorOppg1.calculatePrice("M1", "2021-07-13T17:00:00.000", "2021-07-13T18:00:00.000"));
        Assertions.assertEquals(75, ParkingCalculatorOppg1.calculatePrice("M1", "2021-07-13T17:00:00.000", "2021-07-13T18:15:00.000"));
        Assertions.assertEquals(1440, ParkingCalculatorOppg1.calculatePrice("M1", "2021-07-13T17:00:00.000", "2021-07-14T17:00:00.000"));
        Assertions.assertEquals(1560, ParkingCalculatorOppg1.calculatePrice("M1", "2021-07-13T17:00:00.000", "2021-07-14T19:00:00.000"));
        Assertions.assertEquals(1570, ParkingCalculatorOppg1.calculatePrice("M1", "2021-07-13T17:00:00.000", "2021-07-14T19:10:00.000"));
    }

    //Oppgave 2 to 4 Calculator

    //
    // Test, zone M1
    //
    @Test
    void testM1_Reg() {
        Parking parking = ParkingCalculatorService.calculatePrice("M1", "2021-07-13T17:00:00", "2021-07-13T17:30:00");
        Assertions.assertEquals(30, parking.getParkingFee());
    }
    //
    // Tests, zone M2
    //
    //Regular weekday
    @Test
    void testM2_Reg() {
        Parking parking = ParkingCalculatorService.calculatePrice("M2", "2021-07-13T17:00:00", "2021-07-13T17:30:00");
        Assertions.assertEquals(50, parking.getParkingFee());
    }

    //Weekend
    @Test
    void testM2_Weekend() {
        Parking parking = ParkingCalculatorService.calculatePrice("M2", "2021-07-10T17:00:00", "2021-07-10T17:30:00");
        Assertions.assertEquals(100, parking.getParkingFee());
    }

    //
    // Tests, zone M3
    //

    //Mon-Sat, 08-16, less than an hour
    @Test
    void testM3_MonSat0816_subHr() {
        Parking parking = ParkingCalculatorService.calculatePrice("M3", "2021-07-10T12:00:00", "2021-07-10T12:30:00");
        Assertions.assertEquals(0, parking.getParkingFee());
    }

    //Mon-Sat, 08-16, more than an hour
    @Test
    void testM3_MonSat0816_ovrHr() {
        //Over en time mellom 08:00-16:00
        Parking parking = ParkingCalculatorService.calculatePrice("M3", "2021-07-10T12:00:00", "2021-07-10T13:30:00");
        Assertions.assertEquals(60, parking.getParkingFee());
    }

    //Man-Sat, outside 08-16 timeframe
    @Test
    void testM3_MonSat_outside0816() {
        Parking parking = ParkingCalculatorService.calculatePrice("M3", "2021-07-10T18:00:00", "2021-07-10T18:30:00");
        Assertions.assertEquals(90, parking.getParkingFee());
    }

    //Sunday
    @Test
    void testM3_Sun() {
        Parking parking = ParkingCalculatorService.calculatePrice("M3", "2021-07-11T18:00:00", "2021-07-11T18:30:00");
        Assertions.assertEquals(0, parking.getParkingFee());
    }



}