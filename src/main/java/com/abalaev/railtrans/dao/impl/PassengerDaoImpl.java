package com.abalaev.railtrans.dao.impl;


import com.abalaev.railtrans.dao.api.PassengerDao;
import com.abalaev.railtrans.model.Passenger;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

@Transactional
@Repository("passengerDao")
public class PassengerDaoImpl extends GenericDaoImpl<Passenger, Integer> implements PassengerDao {

    private static final Logger LOG = Logger.getLogger(PassengerDaoImpl.class);

    @Override
    public List<Passenger> findPassenger(String firstName, String lastName, Date birth) {
        List<Passenger> passengers = null;
        try {
            TypedQuery<Passenger> query;
            query = em.createNamedQuery("Passenger.getPassengers", Passenger.class);
            query.setParameter("first", firstName);
            query.setParameter("last", lastName);
            query.setParameter("b", birth, TemporalType.DATE);
            passengers = query.getResultList();
        } catch (Exception e) {
            LOG.error("Error in finding Passenger by full name and birth");
        }
        return passengers;
    }
}
