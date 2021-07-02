package no.nyseth.oppgave.oppgave1.services;

import no.nyseth.oppgave.oppgave1.model.Parking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ParkingCalculator {
    private Parking parking;

    public static long calculatePrice(String parkingZone, String  parkingStartTime, String parkingEndTime) {
        Parking parking = new Parking();

        //Parse string to localdatetime
        LocalDateTime dateStart = LocalDateTime.parse(parkingStartTime);
        LocalDateTime dateEnd = LocalDateTime.parse(parkingEndTime);

        if (dateEnd.isAfter(dateStart)) {
            /*
            Endre til liste for å mimmicke DB?
            Ta .equals e.l. og se om den matcher, og hente derifra.
             */
            if (parkingZone == "M1") {
                System.out.println("Zone found, rate set");
                parking.setRate(60);
            }

            //Calculate fee
            int parkingRate = parking.getRate();
            long parkingFee = parkingFeeCalc(parkingRate, dateStart, dateEnd);

            //Set and return price.
            parking.setParkingPrice(parkingFee);
            System.out.println("Parking price: " + parking.getParkingPrice() + "kr");
        } else {
            System.out.println("End date is not after start date!");
        }
        return parking.getParkingPrice();
    }

    public static long parkingFeeCalc(long rate, LocalDateTime parkingStartTime, LocalDateTime parkingEndTime) {
        long minuteRate = rate / 60;

        long differenceMin = ChronoUnit.MINUTES.between(parkingStartTime, parkingEndTime);
        long hrsParked = differenceMin / 60;
        long minOutsideHrs = differenceMin % 60;

        System.out.println("Time Parked " + hrsParked + ":" + minOutsideHrs);

        long parkingFeeCalc = ((rate * hrsParked) + (minuteRate * minOutsideHrs));
        return parkingFeeCalc;
    }

    //"M1"
    //"2021-07-13T17:00:00.000"
    //"2021-07-13T18:00:00.000"
    public static void main(String[] args) {
        calculatePrice("M1", "2021-07-13T17:00:00.000", "2021-07-13T19:16:00.000");
    }
}
