package com.abalaev.railtrans.service.api;


import com.abalaev.railtrans.model.Route;

import java.util.List;

public interface RouteService {

    Route createRoute(Route route);

    List<Route> getAllRoutes();

    Route readRoute(int id);
}
