package no.nyseth.oppgave.oppgave2til4.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Parking {
    private String parkingZone;
    private LocalDateTime parkingStartTime;
    private LocalDateTime parkingEndTime;
    private double parkingFee;

    public Parking(String parkingZone, LocalDateTime parkingStartTime, LocalDateTime parkingEndTime, double parkingFee) {
        this.parkingZone = parkingZone;
        this.parkingStartTime = parkingStartTime;
        this.parkingEndTime = parkingEndTime;
        this.parkingFee = parkingFee;
    }

    public Parking(String parkingZone, LocalDateTime parkingStartTime, LocalDateTime parkingEndTime) {
        this.parkingZone = parkingZone;
        this.parkingStartTime = parkingStartTime;
        this.parkingEndTime = parkingEndTime;
    }

    public Parking() {
    }

    public String getParkingZone() {
        return this.parkingZone;
    }

    public void setParkingZone(String parkingZone) {
        this.parkingZone = parkingZone;
    }

    public LocalDateTime getParkingStartTime() {
        return parkingStartTime;
    }

    public void setParkingStartTime(LocalDateTime parkingStartTime) {
        this.parkingStartTime = parkingStartTime;
    }

    public LocalDateTime getParkingEndTime() {
        return parkingEndTime;
    }

    public void setParkingEndTime(LocalDateTime parkingEndTime) {
        this.parkingEndTime = parkingEndTime;
    }

    public double getParkingFee() {
        return parkingFee;
    }

    public void setParkingFee(double parkingFee) {
        this.parkingFee = parkingFee;
    }
}
