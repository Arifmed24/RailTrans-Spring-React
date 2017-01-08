package com.abalaev.railtrans.dao.api;

import com.abalaev.railtrans.model.Route;

import java.util.List;

public interface RouteDao extends GenericDao<Route, Integer> {
    List<Route> getAll();
}
