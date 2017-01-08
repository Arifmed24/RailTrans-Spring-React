package com.abalaev.railtrans.service.api;


import com.abalaev.railtrans.model.Train;

import java.util.List;

public interface TrainService {

    Train createTrain(Train train);

    List<Train> getAllTrains();

    Train read(int id);
}
