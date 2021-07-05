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

    /**
     * Takes three parameters
     * @param parkingZone - Parking Zone
     * @param parkingStartTime - What time the parking starts
     * @param parkingEndTime - What time the parking stops.
     * Declares/Initializes some used variables.
     * Adds parkingZone to ArrayList (not ideal way to solve it, but this was a way it worked)
     * Parses dates to LocalDateTime, the parameters are passed as Strings beforehand.
     * Runs various checks, throws exception of failed.
     * Calculates fee
     * @return - Returns the calculated parking fee
     */
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

    /**
     * @param rate - The rate of the zone
     * @param parkingStartTime - Start time of parking
     * @param parkingEndTime - End time of parking
     * Takes the rate from the parameters and calculates the minute rate from it.
     * Calculates the time between the two timestamps to see how long the parking was/is.
     * Divides the returned number to find amount of hrs parked.
     * Uses modulo to find the remaining minutes outside hr.
     * Calculates the fee
     * @return - The parking fee
     */
    public static double parkingFeeCalc(double rate, LocalDateTime parkingStartTime, LocalDateTime parkingEndTime) {
        double minuteRate = rate / 60;

        long differenceMin = ChronoUnit.MINUTES.between(parkingStartTime, parkingEndTime);
        long hrsParked = differenceMin / 60;
        long minOutsideHrs = differenceMin % 60;

        System.out.println("Time Parked " + hrsParked + ":" + minOutsideHrs);

        double parkingFeeCalculated = ((rate * hrsParked) + (minuteRate * minOutsideHrs));
        return parkingFeeCalculated;
    }

    //Method to test internally.
    public static void main(String[] args) {
        //calculatePrice("M1", "2021-07-13T17:00:00.000", "2021-07-13T19:10:00.000");
    }
}
