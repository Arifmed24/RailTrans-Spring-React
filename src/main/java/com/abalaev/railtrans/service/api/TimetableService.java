package com.abalaev.railtrans.service.api;


import com.abalaev.railtrans.model.RouteTimetables;
import com.abalaev.railtrans.model.Station;
import com.abalaev.railtrans.model.Timetable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface TimetableService {

    Timetable readByStations(Station stationBegin, Station stationEnd) throws Exception;

    Set<Timetable> getTimetableListFromRouteTimetables(List<RouteTimetables> routeTimetablesList);

    ArrayList getRelatedStations(List<Station> stations, Set<Timetable> timetables, int numberOfStations);
}
