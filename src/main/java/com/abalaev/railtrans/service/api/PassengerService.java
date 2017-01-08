package com.abalaev.railtrans.service.api;


import com.abalaev.railtrans.model.Passenger;
import com.abalaev.railtrans.model.RouteTimetables;

import java.util.List;
import java.util.Set;

public interface PassengerService {

    boolean isExists(Passenger passenger);

    Passenger create(Passenger passenger);

    Passenger getByNameAndBirth(Passenger passenger);

    Set<Passenger> getPassengersOfRoute(List<RouteTimetables> timetables);
}
