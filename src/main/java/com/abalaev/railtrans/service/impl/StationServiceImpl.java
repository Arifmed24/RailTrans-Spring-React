package com.abalaev.railtrans.service.impl;

import com.abalaev.railtrans.dao.api.StationDao;
import com.abalaev.railtrans.model.Route;
import com.abalaev.railtrans.model.Station;
import com.abalaev.railtrans.service.api.StationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("stationService")
@Transactional
public class StationServiceImpl implements StationService {

    private static final Logger LOG = Logger.getLogger(StationServiceImpl.class);

    private final StationDao stationDao;

    @Autowired
    public StationServiceImpl(StationDao stationDao) {
        this.stationDao = stationDao;
    }

    @Override
    public List<Station> getAllStations() {
        return stationDao.getAll();
    }

    @Override
    public Station read(int id) {
        Station result;
        result = stationDao.read(id);
        return result;
    }

    @Override
    public Station createStation(Station station) {
        Station result;
        result = stationDao.create(station);
        LOG.info("station created");
        return result;
    }

    @Override
    public Station updateStation(Station station) {
        Station result;
        result = stationDao.update(station);
        LOG.info("station updated ");
        return result;
    }

    @Override
    public List<Station> checkStationsInRoute(Route route, List<String> stationsId) throws Exception {
        LOG.info("start checking stations in route");
        List<Station> result = new ArrayList<>();
        result.add(route.getStartStation());
        result.add(route.getFinishStation());
        for (String stationId : stationsId) {
            int idStation = Integer.parseInt(stationId);
            Station nextStation = stationDao.read(idStation);
            if (!result.contains(nextStation)) {
                result.add(result.size() - 1, nextStation);
            } else {
                LOG.error("Route has one station more than one time");
                throw new Exception("Route can't have one station twice");
            }
        }
        LOG.info("finish checking stations in route");
        return result;
    }

    @Override
    public Integer getNumberOfStations(List<Station> stations) {
        return stations.size();
    }

    @Override
    public Station findByName(String stationName) {
        return stationDao.findByName(stationName);
    }

    @Override
    public boolean isStationExists(Station station) {
        return findByName(station.getStationName()) != null;
    }
}
