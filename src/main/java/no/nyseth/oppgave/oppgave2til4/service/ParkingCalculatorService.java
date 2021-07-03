package no.nyseth.oppgave.oppgave2til4.service;

import no.nyseth.oppgave.oppgave2til4.model.Parking;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

@Service
public class ParkingCalculatorService {
    static HashMap<String, Double> parkingZoneMap = new HashMap<String, Double>();
    private Parking parking;

    @SuppressWarnings("Duplicates") //Added to remove duplicate notice as some code is reused from task 1.
    public static Parking calculatePrice(String parkingZone, String parkingStartTime, String parkingEndTime) {
        Parking parking = new Parking(); //Create object
        double parkingFee;
        double parkingRate;

        //Populate hashmap
        parkingZoneMap.put("M1", 60.0);
        parkingZoneMap.put("M2", 100.0);

        //Parse string to localdatetime
        // formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateStart = LocalDateTime.parse(parkingStartTime);
        LocalDateTime dateEnd = LocalDateTime.parse(parkingEndTime);

        //Add data to object
        parking.setParkingZone(parkingZone);
        parking.setParkingStartTime(dateStart);
        parking.setParkingEndTime(dateEnd);

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
            System.out.println("Weekend.");
            parkingRate = (parkingZoneMap.get(parkingZone) * 2);
        }

        //Calculate fee and add to object.
        parkingFee = parkingFeeCalc(parkingRate, dateStart, dateEnd);
        parking.setParkingFee(parkingFee);

        System.out.println("Parking price: " + parkingFee + "kr" + dateStart.getDayOfWeek());
        return parking;
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

        System.out.println("Time Parked " + hrsParked + "h" + minOutsideHrs);


        double parkingFeePreRound = ((rate * hrsParked) + (minuteRate * minOutsideHrs)); //Calculate fee
        double parkingFeeCalculated = Math.round(parkingFeePreRound * 100.0) / 100.0; //Round fee to 2 decimal places.

        return parkingFeeCalculated;
    }

    public static void main(String[] args) {
        calculatePrice("M2", "2021-07-10T17:00:00", "2021-07-10T18:00:00");
    }
}
