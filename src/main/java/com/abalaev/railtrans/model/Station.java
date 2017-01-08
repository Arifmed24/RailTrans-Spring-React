package com.abalaev.railtrans.model;

import javax.persistence.*;

@Entity
@Table(name = "Station", schema = "mydb")
@NamedQueries({
        @NamedQuery(name = "Station.findStationByName", query = "SELECT s FROM Station s WHERE s.stationName = :stationName"),
        @NamedQuery(name = "Station.getAll", query = "SELECT s FROM Station s"),
        @NamedQuery(name = "Station.findByName", query = "SELECT s FROM Station s WHERE s.stationName = :stationName")
})
public class Station {
    @Id
    @Column(name = "idStation", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idStation;

    @Column(name = "stationName", nullable = false)
    private String stationName;

    public int getIdStation() {
        return idStation;
    }

    public void setIdStation(int idStation) {
        this.idStation = idStation;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String toString() {
        return "Station{" +
                "idStation=" + idStation +
                ", stationName='" + stationName + '\'' +
                '}';
    }
}
