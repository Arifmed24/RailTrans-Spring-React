package com.abalaev.railtrans.controller;

import com.abalaev.railtrans.model.RouteTimetables;
import com.abalaev.railtrans.model.Station;
import com.abalaev.railtrans.service.api.RouteTimetablesService;
import com.abalaev.railtrans.service.api.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class StationController {

    private final StationService stationService;
    private final RouteTimetablesService routeTimetablesService;

    @Autowired
    public StationController(StationService stationService, RouteTimetablesService routeTimetablesService) {
        this.stationService = stationService;
        this.routeTimetablesService = routeTimetablesService;
    }

    //-------------------Retrieve All Stations--------------------------------------------------------
    @CrossOrigin
    @RequestMapping(value = "/station/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Station>> stations() {
        List<Station> allStations = stationService.getAllStations();
        if (allStations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(allStations, HttpStatus.OK);
    }

    //-------------------Retrieve Single Station--------------------------------------------------------

    @RequestMapping(value = "/station/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Station> getStation(@PathVariable("id") int id) {
        Station station = stationService.read(id);
        if (station == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(station, HttpStatus.OK);
    }

    //-------------------Create a Station--------------------------------------------------------
    @CrossOrigin
    @RequestMapping(value = "/station/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Void> addStation(@RequestBody Station station, UriComponentsBuilder ucBuilder) {
        if (stationService.isStationExists(station)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        stationService.createStation(station);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/station/{id}").buildAndExpand(station.getIdStation()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    //------------------- Update a Station --------------------------------------------------------
    @RequestMapping(value = "/station/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Station> updateStation(@PathVariable("id") int id, @RequestBody Station station) {
        Station currentStation = stationService.read(id);
        if (currentStation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentStation.setStationName(station.getStationName());
        stationService.updateStation(currentStation);
        return new ResponseEntity<>(currentStation, HttpStatus.OK);
    }


    @RequestMapping(value = "/station/timetable/", method = RequestMethod.GET)
    public ResponseEntity<List<Station>> stationTimetable() {
        List<Station> allStations = stationService.getAllStations();
//        Station station = stationService.read(id);
        if (allStations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(allStations, HttpStatus.OK);
    }

    static class StationTimetable {
        public List<RouteTimetables> TimetablesArr;
        public List<RouteTimetables> TimetablesDep;
    }

    @RequestMapping(value = "/station/timetable/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StationTimetable> showTimetableOfStation(@PathVariable("id") int id, @RequestBody String str) throws ServletException, IOException {
        Station station = stationService.read(id);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<RouteTimetables> routeTimetablesArr = routeTimetablesService.getTimetableStationArr(station, date);
        List<RouteTimetables> routeTimetablesDep = routeTimetablesService.getTimetableStationDep(station, date);
        StationTimetable stationTimetable = new StationTimetable();
        stationTimetable.TimetablesArr = routeTimetablesArr;
        stationTimetable.TimetablesDep = routeTimetablesDep;
        return new ResponseEntity<>(stationTimetable, HttpStatus.OK);
    }

}
