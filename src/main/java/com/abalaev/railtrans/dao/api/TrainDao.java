package com.abalaev.railtrans.dao.api;

import com.abalaev.railtrans.model.Train;

import java.util.List;

public interface TrainDao extends GenericDao<Train, Integer> {
    List<Train> getAllTrains();
}
