package com.abalaev.railtrans.dao.impl;

import com.abalaev.railtrans.dao.api.RouteDao;
import com.abalaev.railtrans.model.Route;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Transactional
@Repository("routeDao")
public class RouteDaoImpl extends GenericDaoImpl<Route, Integer> implements RouteDao {

    private static final Logger LOG = Logger.getLogger(RouteDaoImpl.class);

    @Override
    public List<Route> getAll() {
        List<Route> result = null;
        try {
            TypedQuery<Route> query;
            query = em.createNamedQuery("Route.getAll", Route.class);
            result = query.getResultList();
        } catch (Exception e) {
            LOG.error("Error in getting all routes");
        }
        return result;
    }
}
