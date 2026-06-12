package com.rishav.trainreservation.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PassengerDetailsForm {

    private Long trainId;

    private String fromStation;

    private String toStation;

    private Double fare;

    private LocalDate journeyDate;

    private Integer passengerCount;

    private List<PassengerForm> passengers =
            new ArrayList<>();

    public PassengerDetailsForm() {
    }

    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    public String getFromStation() {
        return fromStation;
    }

    public void setFromStation(String fromStation) {
        this.fromStation = fromStation;
    }

    public String getToStation() {
        return toStation;
    }

    public void setToStation(String toStation) {
        this.toStation = toStation;
    }

    public Double getFare() {
        return fare;
    }

    public void setFare(Double fare) {
        this.fare = fare;
    }

    public LocalDate getJourneyDate() {
        return journeyDate;
    }

    public void setJourneyDate(LocalDate journeyDate) {
        this.journeyDate = journeyDate;
    }

    public Integer getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(Integer passengerCount) {
        this.passengerCount = passengerCount;
    }

    public List<PassengerForm> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<PassengerForm> passengers) {
        this.passengers = passengers;
    }
}