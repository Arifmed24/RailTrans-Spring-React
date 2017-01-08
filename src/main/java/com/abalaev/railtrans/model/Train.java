package com.abalaev.railtrans.model;

import javax.persistence.*;

@Entity
@Table(name = "Train", schema = "mydb")
@NamedQuery(name = "Train.getAllTrains", query = "SELECT t FROM Train t")
public class Train {
    @Id
    @Column(name = "idTrain", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTrain;

    @Column(name = "seats", nullable = false)
    private int seats;

    public int getIdTrain() {
        return idTrain;
    }

    public void setIdTrain(int idTrain) {
        this.idTrain = idTrain;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Train{" +
                "idTrain=" + idTrain +
                ", seats=" + seats +
                '}';
    }
}

