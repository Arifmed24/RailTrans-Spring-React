package com.abalaev.railtrans.dao.api;

import com.abalaev.railtrans.model.Route;
import com.abalaev.railtrans.model.RouteTimetables;
import com.abalaev.railtrans.model.Station;

import java.util.Date;
import java.util.List;

public interface RouteTimetablesDao extends GenericDao<RouteTimetables, Integer> {
    List<RouteTimetables> getAll();
    List<RouteTimetables> getStationTimetableArr(Station station, Date dateBegin, Date dateEnd);
    List<RouteTimetables> getStationTimetableDep(Station station, Date dateBegin, Date dateEnd);
    List<RouteTimetables> getRoutes();
    List<RouteTimetables> getRouteTimetableByRouteAndNumberInRoute(Route route, int number, Date dateBegin, Date dateEnd);
    List<RouteTimetables> getListRtByRoute (Route route);
    List<RouteTimetables> getRoutesWithPassengers(Route route, int number, Date dateBegin, Date dateEnd);
    List<RouteTimetables> getRouteTimetablesInPeriod(Date dateBegin, Date dateEnd);
}
