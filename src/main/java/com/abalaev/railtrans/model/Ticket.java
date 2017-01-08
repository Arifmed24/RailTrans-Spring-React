package com.abalaev.railtrans.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Ticket", schema = "mydb")
public class Ticket {
    @Id
    @Column(name = "idTicket", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTicket;

    @ManyToOne
    @JoinColumn(name = "ticket_train")
    private Train ticketTrain;

    @ManyToOne
    @JoinColumn(name = "ticket_passenger")
    private Passenger ticketPassenger;

    @ManyToOne
    @JoinColumn(name = "departure_station")
    private Station departureStation;

    @ManyToOne
    @JoinColumn(name = "arrival_station")
    private Station arrivalStation;

    @Column(name = "departure_date", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date departureDate;

    @Column(name = "arrival_date", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivalDate;

    @Column(name = "Price", columnDefinition = "Decimal(19,4)")
    private java.math.BigDecimal price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "booked_timetables", joinColumns = {
            @JoinColumn(name = "ticket_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "event_id",
                    nullable = false)})
    @JsonManagedReference
    private Set<RouteTimetables> routeTimetables = new HashSet<>();

    public Set<RouteTimetables> getRouteTimetables() {
        return routeTimetables;
    }

    public void setRouteTimetables(Set<RouteTimetables> routeTimetables) {
        this.routeTimetables = routeTimetables;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "idTicket=" + idTicket +
                ", ticketTrain=" + ticketTrain +
                ", ticketPassenger=" + ticketPassenger +
                ", departureStation=" + departureStation +
                ", arrivalStation=" + arrivalStation +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                ", price=" + price +
                '}';
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public Train getTicketTrain() {
        return ticketTrain;
    }

    public void setTicketTrain(Train ticketTrain) {
        this.ticketTrain = ticketTrain;
    }

    public Passenger getTicketPassenger() {
        return ticketPassenger;
    }

    public void setTicketPassenger(Passenger ticketPassenger) {
        this.ticketPassenger = ticketPassenger;
    }

    public Station getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(Station departureStation) {
        this.departureStation = departureStation;
    }

    public Station getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(Station arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
