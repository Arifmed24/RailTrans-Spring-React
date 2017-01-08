package com.abalaev.railtrans.dao.impl;

import com.abalaev.railtrans.dao.api.TrainDao;
import com.abalaev.railtrans.model.Train;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository("trainDao")
@Transactional
public class TrainDaoImpl extends GenericDaoImpl<Train, Integer> implements TrainDao {

    private static final Logger LOG = Logger.getLogger(TrainDaoImpl.class);

    @Override
    public List<Train> getAllTrains() {
        List<Train> result = null;
        try {
            TypedQuery<Train> query;
            query = em.createNamedQuery("Train.getAllTrains", Train.class);
            result = query.getResultList();
        } catch (Exception e) {
            LOG.error("Error in getting all trains");
        }
        return result;
    }
}
