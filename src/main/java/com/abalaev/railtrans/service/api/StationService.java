package com.abalaev.railtrans.service.api;


import com.abalaev.railtrans.model.Route;
import com.abalaev.railtrans.model.Station;

import java.util.List;

public interface StationService {

    List<Station> getAllStations();

    Station read(int id);

    Station createStation(Station station);

    Station updateStation(Station station);

    List<Station> checkStationsInRoute(Route route, List<String> stationsId) throws Exception;

    Integer getNumberOfStations(List<Station> stations);

    boolean isStationExists(Station station);

    Station findByName(String stationName);
}
