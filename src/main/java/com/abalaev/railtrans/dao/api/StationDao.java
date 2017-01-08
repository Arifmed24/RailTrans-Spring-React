package com.abalaev.railtrans.dao.api;

import com.abalaev.railtrans.model.Station;

import java.util.List;

public interface StationDao extends GenericDao<Station, Integer> {
    List<Station> getAll();
    Station findByName(String stationName);
}
