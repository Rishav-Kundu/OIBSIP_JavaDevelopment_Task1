
package com.rishav.trainreservation.entity;

public class SearchResult {

    private Train train;
    private String fromStation;
    private String toStation;
    private double fare;

    public SearchResult(
            Train train,
            String fromStation,
            String toStation,
            double fare) {

        this.train = train;
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.fare = fare;
    }

    public Train getTrain() {
        return train;
    }

    public String getFromStation() {
        return fromStation;
    }

    public String getToStation() {
        return toStation;
    }

    public double getFare() {
        return fare;
    }
}

