package com.abalaev.railtrans.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Route_Timetables", schema = "mydb")
@NamedQueries({
        @NamedQuery(name = "RouteTimetables.getAll", query = "SELECT r FROM RouteTimetables r"),
        @NamedQuery(name = "RouteTimetables.getStationTimetableArr",
                query = "SELECT rt FROM RouteTimetables rt INNER JOIN rt.line l " +
                        " WHERE l.stationArrival =:station " +
                        " AND rt.dateArrival between :date1 AND:date2"),
        @NamedQuery(name = "RouteTimetables.getStationTimetableDep",
                query = "SELECT rt FROM RouteTimetables rt INNER JOIN rt.line l " +
                        " WHERE l.stationDeparture =:station " +
                        " AND rt.dateDeparture between :date1 AND:date2"),
        @NamedQuery(name = "RouteTimetables.getRoutes", query = "SELECT r FROM RouteTimetables r order by r.numberInRoute"),
        @NamedQuery(name = "RouteTimetables.getRouteTimetableByRouteAndNumberInRoute",
                query = "SELECT r FROM RouteTimetables r WHERE r.routeId =:route AND r.numberInRoute = :number "
                        + "AND r.dateDeparture > :dateBegin "
                        + "AND r.dateArrival < :dateEnd AND r.freeSeats > 0 order by r.dateDeparture"),
        @NamedQuery(name = "RouteTimetables.getListRtByRoute", query = "SELECT r FROM RouteTimetables r WHERE r.routeId =:route GROUP BY r.routeId, r.numberInRoute"),
        @NamedQuery(name = "RouteTimetables.getRoutesWithPassengers",
                query = "SELECT r FROM RouteTimetables r WHERE r.routeId =:route AND r.numberInRoute = :number "
                        + "AND r.dateDeparture > :dateBegin "
                        + "AND :dateEnd > r.dateArrival order by r.dateDeparture"),
        @NamedQuery(name = "RouteTimetables.getRouteTimetablesInPeriod",
                query = "SELECT r FROM RouteTimetables r WHERE r.dateDeparture > :dateBegin " +
                        "AND :dateEnd < r.dateArrival")
})
public class RouteTimetables {

    @Id
    @Column(name = "id_event", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEvent;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "line", nullable = false)
    @JsonManagedReference
    private Timetable line;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "route_id", nullable = false)
    @JsonManagedReference
    private Route routeId;

    @Column(name = "number_in_route", nullable = false)
    private int numberInRoute;

    @Column(name = "date_departure", columnDefinition = "DATETIME", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDeparture;

    @Column(name = "date_arrival", nullable = false, columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateArrival;

    @Column(name = "free_seats", nullable = false)
    private int freeSeats;

    @ManyToMany(mappedBy = "routeTimetables", fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Ticket> tickets = new HashSet<>();


    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "RouteTimetables{" +
                "idEvent=" + idEvent +
                ", line=" + line +
                ", routeId=" + routeId +
                ", numberInRoute=" + numberInRoute +
                ", dateDeparture=" + dateDeparture +
                ", dateArrival=" + dateArrival +
                ", freeSeats=" + freeSeats +
                '}';
    }

    public int getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(int freeSeats) {
        this.freeSeats = freeSeats;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public Timetable getLine() {
        return line;
    }

    public void setLine(Timetable line) {
        this.line = line;
    }

    public Route getRouteId() {
        return routeId;
    }

    public void setRouteId(Route routeId) {
        this.routeId = routeId;
    }

    public int getNumberInRoute() {
        return numberInRoute;
    }

    public void setNumberInRoute(int numberInRoute) {
        this.numberInRoute = numberInRoute;
    }

    public Date getDateDeparture() {
        return dateDeparture;
    }

    public void setDateDeparture(Date dateDeparture) {
        this.dateDeparture = dateDeparture;
    }

    public Date getDateArrival() {
        return dateArrival;
    }

    public void setDateArrival(Date dateArrival) {
        this.dateArrival = dateArrival;
    }
}
