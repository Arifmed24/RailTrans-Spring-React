package com.abalaev.railtrans.service.impl;

import com.abalaev.railtrans.dao.api.TimetableDao;
import com.abalaev.railtrans.model.RouteTimetables;
import com.abalaev.railtrans.model.Station;
import com.abalaev.railtrans.model.Timetable;
import com.abalaev.railtrans.service.api.TimetableService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("timetableService")
@Transactional
public class TimetableServiceImpl implements TimetableService {

    private static final Logger LOG = Logger.getLogger(TimetableServiceImpl.class);
    private final TimetableDao timetableDao;

    @Autowired
    public TimetableServiceImpl(TimetableDao timetableDao) {
        this.timetableDao = timetableDao;
    }

    @Override
    public Timetable readByStations(Station stationBegin, Station stationEnd) throws Exception {
        LOG.info("start reading timetable by stations");
        Timetable result = timetableDao.readByStations(stationBegin, stationEnd);
        if (result != null) {
            LOG.info("finish reading timetable by stations");
            return result;
        } else{
            LOG.warn("timetable doesn't exist");
            throw new Exception("Way between " + stationBegin.getStationName()+ " and " + stationEnd.getStationName() + " doesn't exists");
        }
    }

    @Override
    public Set<Timetable> getTimetableListFromRouteTimetables(List<RouteTimetables> routeTimetablesList) {
        Set<Timetable> result = new HashSet<>();
        for (RouteTimetables r :routeTimetablesList) {
            result.add(r.getLine());
        }
        return result;
    }

    @Override
    public ArrayList getRelatedStations(List<Station> stations,Set<Timetable> timetables, int numberOfStations) {
        ArrayList[] adj = new ArrayList[numberOfStations];
        for(int i=0; i<numberOfStations;i++){
            adj[i]=new ArrayList<Station>();
        }

        return null;
    }
}
