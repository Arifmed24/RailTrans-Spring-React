package com.abalaev.railtrans.dao.impl;

import com.abalaev.railtrans.dao.api.RouteTimetablesDao;
import com.abalaev.railtrans.model.Route;
import com.abalaev.railtrans.model.RouteTimetables;
import com.abalaev.railtrans.model.Station;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

@Repository("routeTimetablesDao")
@Transactional
public class RouteTimetablesDaoImpl extends GenericDaoImpl<RouteTimetables, Integer> implements RouteTimetablesDao {

    private static final Logger LOG = Logger.getLogger(RouteTimetablesDaoImpl.class);

    @Override
    public List<RouteTimetables> getAll() {
        List<RouteTimetables> result = null;
        try {
            TypedQuery<RouteTimetables> query;
            query = em.createNamedQuery("RouteTimetables.getAll", RouteTimetables.class);
            result = query.getResultList();
            LOG.info("get all route timetables");
        } catch (Exception e) {
            LOG.error("Error in getting all routeTimetables ", e);
        }
        return result;
    }

    @Override
    public List<RouteTimetables> getStationTimetableArr(Station station, Date dateBegin, Date dateEnd) {
        List<RouteTimetables> result = null;
        try {
            TypedQuery<RouteTimetables> query;
            query = em.createNamedQuery("RouteTimetables.getStationTimetableArr", RouteTimetables.class);
            query.setParameter("station", station);
            query.setParameter("date1", dateBegin);
            query.setParameter("date2", dateEnd);
            result = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOG.error("Error in getting station timetable", e);
        }
        return result;
    }

    @Override
    public List<RouteTimetables> getStationTimetableDep(Station station, Date dateBegin, Date dateEnd) {
        List<RouteTimetables> result = null;
        try {
            TypedQuery<RouteTimetables> query;
            query = em.createNamedQuery("RouteTimetables.getStationTimetableDep", RouteTimetables.class);
            query.setParameter("station", station);
            query.setParameter("date1", dateBegin);
            query.setParameter("date2", dateEnd);
            result = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
        return result;
    }

    @Override
    public List<RouteTimetables> getRoutes() {
        List<RouteTimetables> result = null;
        try {
            TypedQuery<RouteTimetables> query;
            query = em.createNamedQuery("RouteTimetables.getRoutes", RouteTimetables.class);
            result = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
        return result;
    }

    @Override
    public List<RouteTimetables> getRouteTimetableByRouteAndNumberInRoute(Route route, int number, Date dateBegin, Date dateEnd) {
        List<RouteTimetables> result = null;
        try {
            TypedQuery<RouteTimetables> query;
            query = em.createNamedQuery("RouteTimetables.getRouteTimetableByRouteAndNumberInRoute", RouteTimetables.class);
            query.setParameter("route", route);
            query.setParameter("number", number);
            query.setParameter("dateBegin", dateBegin, TemporalType.TIMESTAMP);
            query.setParameter("dateEnd", dateEnd, TemporalType.TIMESTAMP);
            result = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
        return result;
    }

    @Override
    public List<RouteTimetables> getListRtByRoute(Route route) {
        LOG.info("start getting list of route timetables by route ");
        List<RouteTimetables> result = null;
        try {
            TypedQuery<RouteTimetables> query;
            query = em.createNamedQuery("RouteTimetables.getListRtByRoute", RouteTimetables.class);
            query.setParameter("route", route);
            result = query.getResultList();
            LOG.info("finished getting list of route timetables by route");
        } catch (Exception e) {
            LOG.error("error in getting list of route timetables by route", e);
            em.getTransaction().rollback();
        }
        return result;
    }

    @Override
    public List<RouteTimetables> getRoutesWithPassengers(Route route, int number, Date dateBegin, Date dateEnd) {
        List<RouteTimetables> result = null;
        try {
            TypedQuery<RouteTimetables> query;
            query = em.createNamedQuery("RouteTimetables.getRoutesWithPassengers", RouteTimetables.class);
            query.setParameter("route", route);
            query.setParameter("number", number);
            query.setParameter("dateBegin", dateBegin, TemporalType.TIMESTAMP);
            query.setParameter("dateEnd", dateEnd, TemporalType.TIMESTAMP);
            result = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
        return result;
    }

    @Override
    public List<RouteTimetables> getRouteTimetablesInPeriod(Date dateBegin, Date dateEnd) {
        List<RouteTimetables> result = null;
        try {
            TypedQuery<RouteTimetables> query;
            query = em.createNamedQuery("RouteTimetables.getRouteTimetablesInPeriod", RouteTimetables.class);
            query.setParameter("dateBegin", dateBegin, TemporalType.TIMESTAMP);
            query.setParameter("dateEnd", dateEnd, TemporalType.TIMESTAMP);
            result = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
        return result;
    }

}
