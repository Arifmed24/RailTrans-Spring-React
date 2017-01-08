package com.abalaev.railtrans.controller;

import com.abalaev.railtrans.model.RouteTimetables;
import com.abalaev.railtrans.model.Station;
import com.abalaev.railtrans.model.Ticket;
import com.abalaev.railtrans.service.api.RouteTimetablesService;
import com.abalaev.railtrans.service.api.StationService;
import com.abalaev.railtrans.service.api.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class RouteTimetablesController {

    private final StationService stationService;
    private final RouteTimetablesService routeTimetablesService;
    private final TicketService ticketService;

    @Autowired
    public RouteTimetablesController(StationService stationService, RouteTimetablesService routeTimetablesService, TicketService ticketService) {
        this.stationService = stationService;
        this.routeTimetablesService = routeTimetablesService;
        this.ticketService = ticketService;
    }

    @RequestMapping(value = "/findway", method = RequestMethod.GET)
    public ResponseEntity<List<Station>> findWayForm() {
        List<Station> stations = stationService.getAllStations();
        if (stations.isEmpty()) {
            return new ResponseEntity<List<Station>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Station>>(stations, HttpStatus.OK);
    }

    static class Variants {
        public List<List<RouteTimetables>> ways;
        public List<Ticket> tickets;
    }

    static class WayRequest {
        public Station stationDep;
        public Station stationArr;
        public String dDep;
        public String dArr;
        public String search;
    }

    @CrossOrigin
    @RequestMapping(value = "/findway", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Variants> findWay(@RequestBody WayRequest wayRequest) throws IOException {
        Station stationDep = wayRequest.stationDep;
        Station stationArr = wayRequest.stationArr;
        String dDep = wayRequest.dDep;
        String dArr = wayRequest.dArr;
        String search = wayRequest.search;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dateDep = null;
        Date dateArr = null;
        try {
            dateDep = sdf.parse(dDep);
            dateArr = sdf.parse(dArr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Variants variants = new Variants();
        if (search.equals("ways")) {
            List<List<RouteTimetables>> ways = routeTimetablesService.findWay(stationDep, stationArr, dateDep, dateArr);
            List<Ticket> tickets = ticketService.getTicketsFromRtLists(ways);
            variants.ways = ways;
            variants.tickets = tickets;
            return new ResponseEntity<>(variants, HttpStatus.OK);
        } else {
            List<List<RouteTimetables>> ways = routeTimetablesService.findWay2(stationDep, stationArr, dateDep, dateArr);
            List<Ticket> tickets = ticketService.getTicketsFromRtLists(ways);
            variants.ways = ways;
            variants.tickets = tickets;
            return new ResponseEntity<>(variants, HttpStatus.OK);
        }
    }
}
