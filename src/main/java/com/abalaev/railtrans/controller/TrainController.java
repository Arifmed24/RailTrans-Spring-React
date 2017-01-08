package com.abalaev.railtrans.controller;

import com.abalaev.railtrans.model.Train;
import com.abalaev.railtrans.service.api.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Controller
public class TrainController {

    private final TrainService trainService;

    @Autowired
    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    //-------------------Retrieve All Trains--------------------------------------------------------
    @CrossOrigin
    @RequestMapping(value = "/train/", method = RequestMethod.GET)
    public ResponseEntity<List<Train>> getAllTrains() {
        List<Train> trains = trainService.getAllTrains();
        if (trains.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(trains, HttpStatus.OK);
    }

    //-------------------Retrieve Single Train--------------------------------------------------------

    @RequestMapping(value = "/train/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Train> getTrain(@PathVariable("id") int id) {
        Train train = trainService.read(id);
        if (train == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(train, HttpStatus.OK);
    }


    //-------------------Create a Train--------------------------------------------------------
    @RequestMapping(value = "/train/", method = RequestMethod.POST)
    public ResponseEntity<Void> addTrain(@RequestBody Train train, UriComponentsBuilder ucBuilder) {
        trainService.createTrain(train);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/train/{id}").buildAndExpand(train.getIdTrain()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
