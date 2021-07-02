package no.nyseth.oppgave.oppgave1.services;

import no.nyseth.oppgave.oppgave1.model.Parking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ParkingCalculator {
    private Parking parking;

    public static long calculatePrice(String parkingZone, String  parkingStartTime, String parkingEndTime) {
        Parking parking = new Parking();

        /*
        Endre til liste for å mimmicke DB?
        Ta .equals e.l. og se om den matcher, og hente derifra.
         */
        if (parkingZone == "M1") {
            System.out.println("Zone found, rate set");
            parking.setRate(60);
        }

        //Parse string to localdatetime
        LocalDateTime dateStart = LocalDateTime.parse(parkingStartTime);
        LocalDateTime dateEnd = LocalDateTime.parse(parkingEndTime);

        //Find timediff
        long parkingTime = timeDifference(dateStart, dateEnd);
        parking.setParkingTime(parkingTime);
        System.out.println("Parking time: " + parkingTime);

        //Calculate fee
        int parkingRate = parking.getRate();
        long parkingFeeCalc = (parkingRate * parkingTime);
        System.out.println("Parking fee: " + parkingFeeCalc);

        //Set and return price.
        parking.setParkingPrice(parkingFeeCalc);
        System.out.println("Parking price: " + parking.getParkingPrice());
        return parking.getParkingPrice();
    }

    /*
    public LocalDateTime dateTimeParser(String parkingDate) throws Exception {
        LocalDateTime localDateTime = LocalDateTime.parse(parkingDate);
        return localDateTime;
    }*/

    public static long timeDifference(LocalDateTime parkingStartTime, LocalDateTime parkingEndTime) {
        long differenceHrs = ChronoUnit.MINUTES.between(parkingStartTime, parkingEndTime);
        return differenceHrs;
    }


    //"M1"
    //"2021-07-13T17:00:00.000"
    //"2021-07-13T18:00:00.000"
    public static void main(String[] args) {
        calculatePrice("M1", "2021-07-13T17:00:00.000", "2021-07-13T18:00:00.000");
    }
}
