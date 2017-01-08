package com.abalaev.railtrans.service.api;


import com.abalaev.railtrans.model.Route;
import com.abalaev.railtrans.model.RouteTimetables;
import com.abalaev.railtrans.model.Station;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface RouteTimetablesService {

    RouteTimetables createRoutetimetable(RouteTimetables routeTimetables);

    List<RouteTimetables> getTimetableStationArr(Station station, Date date);

    List<RouteTimetables> getTimetableStationDep(Station station, Date date);

    Map<Integer, List<Integer>> getRoutes();

    List<List<RouteTimetables>> findWay(Station stationBegin, Station stationEnd, Date dateBegin, Date dateEnd);

    RouteTimetables updateRouteTimetable(RouteTimetables routeTimetables);

    List<RouteTimetables> createTemplateOfGraphic(Route route);

    List<RouteTimetables> addDateInGraphic(List<Date> dates, Route route) throws Exception;

    List<RouteTimetables> createGraphic(List<RouteTimetables> routeTimetables);

    List<Date> checkDatesInRoute(List<String> dates) throws Exception;

    List<List<RouteTimetables>> findWay2(Station stationBegin, Station stationEnd, Date dateBegin, Date dateEnd);

    List<RouteTimetables> getRouteTimetablesInPeriod(Date dateBegin, Date dateEnd);
}
