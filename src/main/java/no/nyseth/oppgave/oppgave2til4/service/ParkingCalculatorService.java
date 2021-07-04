package no.nyseth.oppgave.oppgave2til4.service;

import no.nyseth.oppgave.oppgave2til4.model.Parking;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

@Service
public class ParkingCalculatorService {
    static ArrayList<String> parkingZoneList = new ArrayList<>();
    private Parking parking;

    @SuppressWarnings("Duplicates") //Added to remove duplicate notice as some code is reused from task 1.
    public static Parking calculatePrice(String parkingZone, String parkingStartTime, String parkingEndTime) {
        Parking parking = new Parking(); //Create object

        //Declare/initialize variables.
        double parkingFee;
        double parkingRate = 0;

        //Populate ArrayList
        parkingZoneList.add("M1");
        parkingZoneList.add("M2");
        parkingZoneList.add("M3");

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
        if (!parkingZoneList.contains(parkingZone)) {
            System.out.println("Zone not found");
            throw new IllegalArgumentException("Zone not found");
        }

        if (parking.getParkingZone().equals("M1")) {
            System.out.println("M1 detected.");
            parkingRate = 60;
        }

        //Check if zone is m2 and if it's weekend.
        if (parking.getParkingZone().equals("M2")) {
            System.out.println("M2 detected.");
            parkingRate = 100;
            if (checkWeekend(dateStart)) {
                parkingRate = 200;
            }
        }

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
    public static double parkingCalcZoneM3(LocalDateTime parkingStartTime, LocalDateTime parkingEndTime) {
        System.out.println("M3 detected");
        double minuteRate;

        long differenceMin = ChronoUnit.MINUTES.between(parkingStartTime, parkingEndTime);
        System.out.println("Min: " + differenceMin);
        long hrsParked = differenceMin / 60;

        DayOfWeek currentDay = parkingEndTime.getDayOfWeek();

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

    //Method to test internally.
    public static void main(String[] args) {
        calculatePrice("M2", "2021-07-11T17:00:00", "2021-07-11T18:00:00");
        System.out.println(parkingZoneList.toString());
    }
}
