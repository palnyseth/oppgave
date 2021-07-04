package no.nyseth.oppgave.oppgave1;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * Løsning for Oppgave 1
 * Kalkulator for takstsone M1
 */
public class ParkingCalculatorOppg1 {

    //ArrayList is used to mimmick a database to check for zone.
    static ArrayList<String> parkingZoneList = new ArrayList<>();

    public static double calculatePrice(String parkingZone, String  parkingStartTime, String parkingEndTime) {

        //Declare/initialize variables.
        double parkingFee;
        double parkingRate = 0;

        //Populate ArrayList -
        // Has to be somewhere here for some reason for tests and other internal tests to work.
        // GET-requests works without it.
        parkingZoneList.add("M1");

        //Parse string to localdatetime
        LocalDateTime dateStart = LocalDateTime.parse(parkingStartTime);
        LocalDateTime dateEnd = LocalDateTime.parse(parkingEndTime);

        if (!dateEnd.isAfter(dateStart)) {
            System.out.println("End date is not after start date!");
            throw new IllegalArgumentException("End date is not after start date!");
        }

        //Check if zone exists and retrieve rate
        if (!parkingZoneList.contains(parkingZone)) {
            System.out.println("Zone not found");
            throw new IllegalArgumentException("Zone not found");
        }

        if (parkingZone.equals("M1")) {
            System.out.println("M1 detected.");
            parkingRate = 60;
        }

        //Calculate fee
        parkingFee = parkingFeeCalc(parkingRate, dateStart, dateEnd);

        //return price.
        System.out.println("Parking price: " + parkingFee + "kr");
        return parkingFee;
    }

    public static double parkingFeeCalc(double rate, LocalDateTime parkingStartTime, LocalDateTime parkingEndTime) {
        double minuteRate = rate / 60;

        long differenceMin = ChronoUnit.MINUTES.between(parkingStartTime, parkingEndTime);
        long hrsParked = differenceMin / 60;
        long minOutsideHrs = differenceMin % 60;

        System.out.println("Time Parked " + hrsParked + ":" + minOutsideHrs);

        double parkingFeeCalculated = ((rate * hrsParked) + (minuteRate * minOutsideHrs));
        return parkingFeeCalculated;
    }

    public static void main(String[] args) {
        calculatePrice("M1", "2021-07-13T17:00:00.000", "2021-07-13T19:10:00.000");
    }
}
