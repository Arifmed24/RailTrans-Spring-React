package com.abalaev.railtrans.service.impl;

import com.abalaev.railtrans.dao.api.RouteDao;
import com.abalaev.railtrans.model.Route;
import com.abalaev.railtrans.service.api.RouteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("routeService")
@Transactional
public class RouteServiceImpl implements RouteService{

    private static final Logger LOG = Logger.getLogger(RouteServiceImpl.class);
    private final RouteDao routeDao;

    @Autowired
    public RouteServiceImpl(RouteDao routeDao) {
        this.routeDao = routeDao;
    }

    @Override
    public Route createRoute(Route route) {
        Route result;
        result = routeDao.create(route);
        LOG.info("route created");
        return result;
    }

    @Override
    public List<Route> getAllRoutes() {
        LOG.info("get all routes");
        return routeDao.getAll();
    }

    @Override
    public Route readRoute(int id) {
        LOG.info("read route");
        return routeDao.read(id);
    }
}
