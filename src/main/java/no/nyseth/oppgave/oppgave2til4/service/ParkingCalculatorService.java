package no.nyseth.oppgave.oppgave2til4.service;

import no.nyseth.oppgave.oppgave2til4.model.Parking;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;



/*
 Løsning oppgave 2 til 4
 - Løsning involverer andre klasser i oppgave2til4 pakken
 -- ParkingCalcuatorConfig.java
 -- ParkingCalculatorService.java
 -- ParkingCalculatorController.java
 - Tester er i test.java.nyseth.oppgave.ParkingCalculatorTest (sammen med oppgave 1 tester)
 */
@Service
public class ParkingCalculatorService {
    //ArrayList is used to mimmick a database to check for zone.
    static ArrayList<String> parkingZoneList = new ArrayList<>();
    private Parking parking;

    /**
     * Main method used for calculating.
     * - Retrieves data from request
     * - Declares/Initializes variables used.
     * - Creates a Parking object and sets the appropriate data from parameters of method
     * -- start & end date are passed through an parser beforehand as it's being passed as an String in the parameter.
     * - Various checks that throws exception if hit
     * -- If zone is not found, exception is thrown
     * --- ArrayList is used to mimick an DB, idea is that it checks if the zone in the request actually exists
     * --- The ArrayList is being filled in the calculate method, which is not ideal, but had to be done there for tests and internall calls to work (commandLineRunner etc. fixed it work API requests, but not internally).
     * - Calculates the fee (method varies depending on the zone (one for M1/M2 and a different one for M3)
     *
     * @param parkingZone - Parking Zone
     * @param parkingStartTime - What time the parking starts
     * @param parkingEndTime - What time the parking stops.
     * @return the created Parking object with it's properties and calculated parking fee.
     */
    public static Parking calculatePrice(String parkingZone, String parkingStartTime, String parkingEndTime) {
        Parking parking = new Parking(); //Create object

        //Declare/initialize variables.
        double parkingFee;
        double parkingRate = 0;

        // Populates ArrayList
        // Has to be somewhere here for some reason for tests and other internal tests to work.
        // GET-requests works without it.
        parkingZoneList.add("M1");
        parkingZoneList.add("M2");
        parkingZoneList.add("M3");

        //Parse string to localdatetime
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
        if (!parkingZoneList.contains(parkingZone)) {
            System.out.println("Zone not found");
            throw new IllegalArgumentException("Zone not found");
        }

        //Checks if zone is M1
        if (parking.getParkingZone().equals("M1")) {
            System.out.println("M1 detected.");
            parkingRate = 60;
        }

        //Check if zone is M2 and if it's weekend.
        if (parking.getParkingZone().equals("M2")) {
            System.out.println("M2 detected.");
            parkingRate = 100;
            if (checkWeekend(dateStart)) {
                parkingRate = 200;
            }
        }

        //Checks if the zone is M3
        if (parking.getParkingZone().equals("M3")) {
            System.out.println("M3 detected, progressing.");
            parkingFee = parkingCalcZoneM3(dateStart, dateEnd);
            parking.setParkingFee(parkingFee);
            System.out.println("Parking price: " + parkingFee + "kr");
            return parking;
        }

        //Calculate fee and add to object.
        parkingFee = parkingFeeCalc(parkingRate, dateStart, dateEnd);
        parking.setParkingFee(parkingFee);
        System.out.println("Parking price: " + parkingFee + "kr");
        return parking;
    }

    /**
     * Calculates the parking fee for zone M3
     * Takes two parameters
     * @param parkingStartTime - What time the parking started
     * @param parkingEndTime - What time the parking ended
     * @return - The calculated parking fee
     *
     * Uses ChronoUnit to calculate duration of parking in minutes.
     * Divides by 60 to find amount of hrs.
     * Checks currentday, and if currentday is Sunday - As parking is free on sundays in the zone.
     *
     * Runs various checks on time and duration of parking and returns the appropriate minute rate.
     *
     * Calculates the fee
     * @return - The calculated parking fee
     */
    public static double parkingCalcZoneM3(LocalDateTime parkingStartTime, LocalDateTime parkingEndTime) {
        System.out.println("M3 detected");
        double minuteRate;

        long differenceMin = ChronoUnit.MINUTES.between(parkingStartTime, parkingEndTime);
        System.out.println("Min: " + differenceMin);
        long hrsParked = differenceMin / 60;

        //Gets day of startTime
        DayOfWeek currentDay = parkingStartTime.getDayOfWeek();

        //Checks if currentday is Sunday, returns appropriate minute rate if so.
        if (currentDay == DayOfWeek.SUNDAY) {
            minuteRate = 0;
            return minuteRate;
        }

        //Set to be equal to, or less than 15, which makes it last until 15:59, having 16 would have it valid until 16:59 if done same way.
        if(parkingStartTime.getHour() > 8 && parkingStartTime.getHour() <= 15) {
            if (hrsParked < 1) {
                minuteRate = 0;
            } else {
                minuteRate = 2;
                differenceMin = differenceMin - 60;
            }
        } else {
            minuteRate = 3;
        }

        double parkingFeePreRound = (minuteRate * differenceMin); //Calculate fee
        return parkingFeePreRound;
    }

    /**
     * Boolean method used to check if a date is saturday or sunday.
     * @param localDateTime - Timestamp
     * @return - returns whether or not it's saturday or sunday
     */
    public static boolean checkWeekend(LocalDateTime localDateTime) {
        DayOfWeek day = localDateTime.getDayOfWeek();
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }

    /**
     * Calculates the parking fee (Used for M1 and M2)
     * @param rate - The rate of the parking zone at time of parking
     * @param parkingStartTime - What time the parking starts
     * @param parkingEndTime - What time the parking ends
     * Takes the rate and finds the minuteRate.
     * Finds duration and calculates the hrsParked and minutes outside of hr parked.
     * Calculates, rounds and returns the fee.
     * @return - the calculated parking fee
     */
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

    //Method to test internally.
    public static void main(String[] args) {
        //calculatePrice("M2", "2021-07-11T17:00:00", "2021-07-11T18:00:00");
        //System.out.println(parkingZoneList.toString());
    }
}
