package no.nyseth.oppgave.oppgave1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ParkingCalculatorOppg1Test {
    private static ParkingCalculatorOppg1 parkingCalculatorOppg1;

    @BeforeEach
    void setUp() {
        //Creates and adds zone to hashmap
        HashMap<String, Integer> parkingZoneMap = new HashMap<String, Integer>();
        parkingZoneMap.put("M1", 60);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void testCorrectValue() {
        Assertions.assertEquals(30, ParkingCalculatorOppg1.calculatePrice("M1", "2021-07-13T17:00:00.000", "2021-07-13T17:30:00.000"));
        Assertions.assertEquals(60, ParkingCalculatorOppg1.calculatePrice("M1", "2021-07-13T17:00:00.000", "2021-07-13T18:00:00.000"));
        Assertions.assertEquals(75, ParkingCalculatorOppg1.calculatePrice("M1", "2021-07-13T17:00:00.000", "2021-07-13T18:15:00.000"));
        Assertions.assertEquals(1440, ParkingCalculatorOppg1.calculatePrice("M1", "2021-07-13T17:00:00.000", "2021-07-14T17:00:00.000"));
        Assertions.assertEquals(1560, ParkingCalculatorOppg1.calculatePrice("M1", "2021-07-13T17:00:00.000", "2021-07-14T19:00:00.000"));
        Assertions.assertEquals(1570, ParkingCalculatorOppg1.calculatePrice("M1", "2021-07-13T17:00:00.000", "2021-07-14T19:10:00.000"));
    }

    //Andre format
    /*
        @Test
    void testCorrectValue() {
        Assertions.assertEquals(30, ParkingCalculatorOppg1.calculatePrice("M1", "2021-07-13 17:00:00", "2021-07-13 17:30:00"));
        Assertions.assertEquals(60, ParkingCalculatorOppg1.calculatePrice("M1", "2021-07-13 17:00:00", "2021-07-13 18:00:00"));
        Assertions.assertEquals(75, ParkingCalculatorOppg1.calculatePrice("M1", "2021-07-13 17:00:00", "2021-07-13 18:15:00"));
        Assertions.assertEquals(1440, ParkingCalculatorOppg1.calculatePrice("M1", "2021-07-13 17:00:00", "2021-07-14 17:00:00"));
        Assertions.assertEquals(1560, ParkingCalculatorOppg1.calculatePrice("M1", "2021-07-13 17:00:00", "2021-07-14 19:00:00"));
        Assertions.assertEquals(1570, ParkingCalculatorOppg1.calculatePrice("M1", "2021-07-13 17:00:00", "2021-07-14 19:10:00"));
    }
     */

}