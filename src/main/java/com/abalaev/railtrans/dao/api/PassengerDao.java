package com.abalaev.railtrans.dao.api;

import com.abalaev.railtrans.model.Passenger;

import java.util.Date;
import java.util.List;

public interface PassengerDao extends GenericDao<Passenger, Integer> {
    List<Passenger> findPassenger(String firstName, String lastName, Date birth);
}
