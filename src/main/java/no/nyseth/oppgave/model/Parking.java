package no.nyseth.oppgave.model;

import java.time.LocalDateTime;


/**
 * Modelclass for parking.
 */
public class Parking {
    private String zone; //Name of zone.
    private int rate; //Rate of zone.
    private LocalDateTime startTime; //Time parking started
    private LocalDateTime endTime; //Time parking ended
    private long parkingTime; //Duration of parking based on duration between start & end.
    private long parkingPrice; //Price of parking based on parkingTime and rate.

    /*
    Constructors
     */
    public Parking() {
    }

    public Parking(String zone, LocalDateTime startTime, LocalDateTime endTime) {
        this.zone = zone;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Parking(String zone, int rate, LocalDateTime startTime, LocalDateTime endTime) {
        this.zone = zone;
        this.rate = rate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Parking(String zone, int rate, LocalDateTime startTime, LocalDateTime endTime, long parkingTime, long parkingPrice) {
        this.zone = zone;
        this.rate = rate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.parkingTime = parkingTime;
        this.parkingPrice = parkingPrice;
    }

    /*
    Getters & Setters
     */
    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public long getParkingTime() {
        return parkingTime;
    }

    public void setParkingTime(long parkingTime) {
        this.parkingTime = parkingTime;
    }

    public long getParkingPrice() {
        return parkingPrice;
    }

    public void setParkingPrice(long parkingPrice) {
        this.parkingPrice = parkingPrice;
    }
}
