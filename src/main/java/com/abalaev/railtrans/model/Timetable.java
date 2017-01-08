package com.abalaev.railtrans.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "Timetable", schema = "mydb")
@NamedQuery(name = "Timetable.readByStations", query = "SELECT t FROM Timetable t WHERE t.stationDeparture = :begin AND t.stationArrival = :end")
public class Timetable {
    @Id
    @Column(name = "idLine", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLine;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "station_departure", nullable = false)
    @JsonManagedReference
    private Station stationDeparture;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "station_arrival", nullable = false)
    @JsonManagedReference
    private Station stationArrival;

    @Column(name = "distance", nullable = false)
    private double distance;

    public int getIdLine() {
        return idLine;
    }

    public void setIdLine(int idLine) {
        this.idLine = idLine;
    }

    public Station getStationDeparture() {
        return stationDeparture;
    }

    public void setStationDeparture(Station stationDeparture) {
        this.stationDeparture = stationDeparture;
    }

    public Station getStationArrival() {
        return stationArrival;
    }

    public void setStationArrival(Station stationArrival) {
        this.stationArrival = stationArrival;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Timetable{" +
                "idLine=" + idLine +
                ", stationDeparture=" + stationDeparture +
                ", stationArrival=" + stationArrival +
                ", distance=" + distance +
                '}';
    }
}