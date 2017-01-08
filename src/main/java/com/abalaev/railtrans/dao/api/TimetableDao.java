package com.abalaev.railtrans.dao.api;

import com.abalaev.railtrans.model.Station;
import com.abalaev.railtrans.model.Timetable;

public interface TimetableDao extends GenericDao<Timetable, Integer> {
    Timetable readByStations (Station stationBegin, Station stationEnd);
}
