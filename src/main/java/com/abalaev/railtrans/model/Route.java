package com.abalaev.railtrans.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "Route", schema = "mydb")
@NamedQuery(
        name = "Route.getAll", query = "SELECT r FROM Route r"
)
public class Route {
    @Id
    @Column(name = "idRoute")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRoute;

    @Column(name = "route_name", nullable = false)
    private String routeName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "start_station", nullable = false)
    @JsonManagedReference
    private Station startStation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "finish_station", nullable = false)
    @JsonManagedReference
    private Station finishStation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "train", nullable = false)
    @JsonManagedReference
    private Train train;

    @Override
    public String toString() {
        return "Route{" +
                "idRoute=" + idRoute +
                ", routeName='" + routeName + '\'' +
                ", startStation=" + startStation +
                ", finishStation=" + finishStation +
                ", train=" + train +
                '}';
    }

    public int getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(int idRoute) {
        this.idRoute = idRoute;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public Station getStartStation() {
        return startStation;
    }

    public void setStartStation(Station startStation) {
        this.startStation = startStation;
    }

    public Station getFinishStation() {
        return finishStation;
    }

    public void setFinishStation(Station finishStation) {
        this.finishStation = finishStation;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

}
