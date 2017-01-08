package com.abalaev.railtrans.service.impl;

import com.abalaev.railtrans.dao.api.PassengerDao;
import com.abalaev.railtrans.model.Passenger;
import com.abalaev.railtrans.model.RouteTimetables;
import com.abalaev.railtrans.model.Ticket;
import com.abalaev.railtrans.service.api.PassengerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service("passengerService")
@Transactional
public class PassengerServiceImpl implements PassengerService {

    private static final Logger LOG = Logger.getLogger(PassengerServiceImpl.class);
    private final PassengerDao passengerDao;

    @Autowired
    public PassengerServiceImpl(PassengerDao passengerDao) {
        this.passengerDao = passengerDao;
    }

    @Override
    public boolean isExists(Passenger passenger) {
        int size = passengerDao.findPassenger(passenger.getFirstName(), passenger.getLastName(), passenger.getBirth()).size();
        if (size > 0) {
            LOG.info("passenger exists");
            return true;
        } else {
            LOG.info("passenger doesn't exists");
            return false;
        }
    }

    @Override
    public Passenger create(Passenger passenger) {
        Passenger result;
        result = passengerDao.create(passenger);
        LOG.info("created passenger");
        return result;
    }

    @Override
    public Passenger getByNameAndBirth(Passenger passenger) {
        Passenger result;
        result = passengerDao.findPassenger(passenger.getFirstName(), passenger.getLastName(), passenger.getBirth()).get(0);
        LOG.info("passenger is found");
        return result;
    }

    @Override
    public Set<Passenger> getPassengersOfRoute(List<RouteTimetables> timetables) {
        Set<Passenger> result = new HashSet<>();
        for (RouteTimetables rt : timetables) {
            for (Ticket t : rt.getTickets()) {
                if (t.getTicketPassenger() != null) {
                    result.add(t.getTicketPassenger());
                }
            }
        }
        LOG.info("finding passengers of route");
        return result;
    }
}
