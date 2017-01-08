package com.abalaev.railtrans.controller;

import com.abalaev.railtrans.model.Passenger;
import com.abalaev.railtrans.model.RouteTimetables;
import com.abalaev.railtrans.model.Ticket;
import com.abalaev.railtrans.model.User;
import com.abalaev.railtrans.service.api.PassengerService;
import com.abalaev.railtrans.service.api.RouteTimetablesService;
import com.abalaev.railtrans.service.api.TicketService;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
public class TicketController {

    private final PassengerService passengerService;
    private final RouteTimetablesService routeTimetablesService;
    private final TicketService ticketService;

    @Autowired
    public TicketController(PassengerService passengerService, RouteTimetablesService routeTimetablesService, TicketService ticketService) {
        this.passengerService = passengerService;
        this.routeTimetablesService = routeTimetablesService;
        this.ticketService = ticketService;
    }

    static class TicketRequest {
        @JsonProperty("first")
        public String first;
        @JsonProperty("last")
        public String last;
        @JsonProperty("birth")
        public String birth;
        @JsonProperty("requestTicket")
        public Ticket requestTicket;
        @JsonProperty("way")
        public List<RouteTimetables> way;
        @JsonProperty("user")
        public User user;

        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            this.first = first;
        }

        public String getLast() {
            return last;
        }

        public void setLast(String last) {
            this.last = last;
        }

        public String getBirth() {
            return birth;
        }

        public void setBirth(String birth) {
            this.birth = birth;
        }

        public Ticket getRequestTicket() {
            return requestTicket;
        }

        public void setRequestTicket(Ticket requestTicket) {
            this.requestTicket = requestTicket;
        }

        public List<RouteTimetables> getWay() {
            return way;
        }

        public void setWay(List<RouteTimetables> way) {
            this.way = way;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }

    @RequestMapping(value = "/ticket/new/", method = RequestMethod.POST)
    public ResponseEntity<Ticket> newTicketForm(@RequestBody String s) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TicketRequest ticketRequest = null;

        ticketRequest = new TicketRequest();
        JsonNode root = mapper.readTree(s);
        ticketRequest.setFirst(root.path("first").asText());
        ticketRequest.setLast(root.path("last").asText());
        ticketRequest.setBirth(root.path("birth").asText());
        JsonNode userJson = root.path("user");
//        User user = mapper.treeToValue(userJson,User.class);
        String first = ticketRequest.getFirst();
        String last = ticketRequest.getLast();
        String birthString = ticketRequest.getBirth();

        Ticket requestTicket = null;
        List<RouteTimetables> way = null;
        User user = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date birth = null;
        try {
            birth = sdf.parse(birthString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Passenger passenger = new Passenger();
        passenger.setBirth(birth);
        passenger.setFirstName(first);
        passenger.setLastName(last);
        Passenger ticketPassenger;
        //check passenger. if not exists - creates new one
        if (passengerService.isExists(passenger)) {
            ticketPassenger = passengerService.getByNameAndBirth(passenger);
        } else {
            ticketPassenger = passengerService.create(passenger);
        }

        Set<RouteTimetables> ticketWay = new HashSet<>(way);

        //check registration of this passenger in variant
        Set<Passenger> passengers = passengerService.getPassengersOfRoute(way);
        //flag
        boolean hasPassenger = false;
        //if this passenger exists in variant
        for (Passenger routePassenger : passengers) {
            if (ticketPassenger.equals(routePassenger)) {
                hasPassenger = true;
            }
        }
        if (!hasPassenger) {
            //final ticket
            Ticket ticket = new Ticket();
            ticket.setTicketPassenger(ticketPassenger);
            ticket.setDepartureStation(requestTicket.getDepartureStation());
            ticket.setDepartureDate(requestTicket.getDepartureDate());
            ticket.setArrivalStation(requestTicket.getArrivalStation());
            ticket.setArrivalDate(requestTicket.getArrivalDate());
            ticket.setPrice(requestTicket.getPrice());
            ticket.setTicketTrain(requestTicket.getTicketTrain());
            ticket.setRouteTimetables(ticketWay);
            user.addTicket(ticket);
            ticket = ticketService.createTicket(ticket);

            for (RouteTimetables rt : way) {
                rt.getTickets().add(ticket);
                rt.setFreeSeats(rt.getFreeSeats() - 1);
                routeTimetablesService.updateRouteTimetable(rt);
            }
            return new ResponseEntity<Ticket>(ticket, HttpStatus.CREATED);
        } else {
//            request.setAttribute("error","This passenger is registered yet");
            return new ResponseEntity<Ticket>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/user/tickets", method = RequestMethod.GET)
    public ModelAndView userTickets() {
        ModelAndView modelAndView = new ModelAndView("/ticket/user-tickets");
        modelAndView.addObject("title", "User Tickets");
        return modelAndView;
    }

}