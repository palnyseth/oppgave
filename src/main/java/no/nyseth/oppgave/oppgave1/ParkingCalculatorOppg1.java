package no.nyseth.oppgave.oppgave1;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

/**
 * Løsning for Oppgave 1
 * Kalkulator for takstsone M1
 */
public class ParkingCalculatorOppg1 {
    /*
    Hashmap is used to mimmick a database to check for zone, and retrieve rate.
     */
    static HashMap<String, Integer> parkingZoneMap = new HashMap<String, Integer>();

    public static long calculatePrice(String parkingZone, String  parkingStartTime, String parkingEndTime) {
        long parkingFee;

        //Populate hashmap
        parkingZoneMap.put("M1", 60);

        //Parse string to localdatetime
        LocalDateTime dateStart = LocalDateTime.parse(parkingStartTime);
        LocalDateTime dateEnd = LocalDateTime.parse(parkingEndTime);

        if (!dateEnd.isAfter(dateStart)) {
            System.out.println("End date is not after start date!");
            throw new IllegalArgumentException("End date is not after start date!");
        }

        //Check if zone exists and retrieve rate
        if (!parkingZoneMap.containsKey(parkingZone)) {
            System.out.println("Zone not found");
            throw new IllegalArgumentException("Zone not found");
        }

        int parkingRate = parkingZoneMap.get(parkingZone);

        //Calculate fee
        parkingFee = parkingFeeCalc(parkingRate, dateStart, dateEnd);

        //return price.
        System.out.println("Parking price: " + parkingFee + "kr");
        return parkingFee;
    }

    public static long parkingFeeCalc(long rate, LocalDateTime parkingStartTime, LocalDateTime parkingEndTime) {
        long minuteRate = rate / 60;

        long differenceMin = ChronoUnit.MINUTES.between(parkingStartTime, parkingEndTime);
        long hrsParked = differenceMin / 60;
        long minOutsideHrs = differenceMin % 60;

        System.out.println("Time Parked " + hrsParked + ":" + minOutsideHrs);

        long parkingFeeCalculated = ((rate * hrsParked) + (minuteRate * minOutsideHrs));
        return parkingFeeCalculated;
    }

    public static void main(String[] args) {
        calculatePrice("M1", "2021-07-13T17:00:00.000", "2021-07-14T19:10:00.000");
    }
}
