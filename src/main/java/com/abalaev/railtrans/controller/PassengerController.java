package com.abalaev.railtrans.controller;

import com.abalaev.railtrans.model.Passenger;
import com.abalaev.railtrans.model.RouteTimetables;
import com.abalaev.railtrans.service.api.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Controller
public class PassengerController {
    private final
    PassengerService passengerService;

    @Autowired
    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @RequestMapping(value = "/admin/way/passengers", method = RequestMethod.POST)
    public ResponseEntity<Set<Passenger>> getPassengersOfWay(@RequestBody List<RouteTimetables> way) throws IOException {
        Set<Passenger> passengers = passengerService.getPassengersOfRoute(way);
        return new ResponseEntity<>(passengers, HttpStatus.OK);
    }
}
