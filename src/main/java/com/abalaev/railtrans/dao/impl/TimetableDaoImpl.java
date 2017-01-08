package com.abalaev.railtrans.dao.impl;

import com.abalaev.railtrans.dao.api.TimetableDao;
import com.abalaev.railtrans.model.Station;
import com.abalaev.railtrans.model.Timetable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

@Repository("timetableDao")
@Transactional
public class TimetableDaoImpl extends GenericDaoImpl<Timetable, Integer> implements TimetableDao {
    @Override
    public Timetable readByStations(Station stationBegin, Station stationEnd) {
        Query query = em.createNamedQuery("Timetable.readByStations", Timetable.class);
        query.setParameter("begin", stationBegin);
        query.setParameter("end", stationEnd);
        try {
            return (Timetable) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
