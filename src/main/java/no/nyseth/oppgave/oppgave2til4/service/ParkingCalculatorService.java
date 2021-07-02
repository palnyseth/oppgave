package no.nyseth.oppgave.oppgave2til4.service;

import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class ParkingCalculatorService {
    static HashMap<String, Double> parkingZoneMap = new HashMap<String, Double>();

    @SuppressWarnings("Duplicates") //Added to remove duplicate notice as some code is reused from task 1.
    public static double calculatePrice(String parkingZone, String parkingStartTime, String parkingEndTime) {
        double parkingFee;
        double parkingRate;

        //Populate hashmap
        parkingZoneMap.put("M1", 60.0);
        parkingZoneMap.put("M2", 100.0);


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

        //Check rates from hashmap.
        parkingRate = parkingZoneMap.get(parkingZone);

        //Check if zone is m2 and if it's weekend.
        if (parkingZone == "M2" && checkWeekend(dateStart)) {
            parkingRate = (parkingZoneMap.get(parkingZone) * 2);
        }

        //Calculate fee
        parkingFee = parkingFeeCalc(parkingRate, dateStart, dateEnd);

        System.out.println("Parking price: " + parkingFee + "kr");
        return parkingFee;
    }

    public static boolean checkWeekend(LocalDateTime localDateTime) {
        DayOfWeek day = localDateTime.getDayOfWeek();
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }

    @SuppressWarnings("Duplicates") //Added to remove duplicate notice as some code is reused from task 1.
    public static double parkingFeeCalc(double rate, LocalDateTime parkingStartTime, LocalDateTime parkingEndTime) {
        double minuteRate = rate / 60;

        long differenceMin = ChronoUnit.MINUTES.between(parkingStartTime, parkingEndTime);
        long hrsParked = differenceMin / 60;
        long minOutsideHrs = differenceMin % 60;

        System.out.println("Time Parked " + hrsParked + ":" + minOutsideHrs);


        double parkingFeePreRound = ((rate * hrsParked) + (minuteRate * minOutsideHrs)); //Calculate fee
        double parkingFeeCalculated = Math.round(parkingFeePreRound * 100.0) / 100.0; //Round fee to 2 decimal places.

        return parkingFeeCalculated;
    }

    public static void main(String[] args) {
        calculatePrice("M2", "2021-07-12T17:00:00.000", "2021-07-12T18:10:00.000");
    }
}
