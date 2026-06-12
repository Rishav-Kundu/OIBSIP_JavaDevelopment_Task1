
package com.rishav.trainreservation.repository;

import com.rishav.trainreservation.entity.Train;
import com.rishav.trainreservation.entity.TrainRoute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainRouteRepository
        extends JpaRepository<TrainRoute, Long> {

    List<TrainRoute> findByStationName(String stationName);

    List<TrainRoute> findByTrainOrderByStationOrderAsc(
            Train train);

    long countByTrain(Train train);

    TrainRoute findByTrainAndStationName(
            Train train,
            String stationName);
}

