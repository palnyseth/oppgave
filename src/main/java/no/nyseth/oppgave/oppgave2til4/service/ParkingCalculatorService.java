package no.nyseth.oppgave.oppgave2til4.service;

import java.time.LocalDateTime;
import java.util.HashMap;

public class ParkingCalculatorService {
    static HashMap<String, Integer> parkingZoneMap = new HashMap<String, Integer>();

    @SuppressWarnings("Duplicates") //Added to remove duplicate notice as some code is reused from task 1.
    public static long calculatePrice(String parkingZone, String parkingStartTime, String parkingEndTime) {
        long parkingFee;

        //Populate hashmap
        parkingZoneMap.put("M1", 60);
        parkingZoneMap.put("M2", 100);


        //Parse string to localdatetime
        LocalDateTime dateStart = LocalDateTime.parse(parkingStartTime);
        LocalDateTime dateEnd = LocalDateTime.parse(parkingEndTime);

        //Check if enddate is after startdate.
        if (!dateEnd.isAfter(dateStart)) {
            System.out.println("End date is not after start date!");
            throw new IllegalArgumentException("End date is not after start date!");
        }

        //Check if zone exists and retrieve rate
        if (!parkingZoneMap.containsKey(parkingZone)) {
            System.out.println("Zone not found");
            throw new IllegalArgumentException("Zone not found");
        }


        parkingFee = 2; //Temp
        return parkingFee;
    }
}
