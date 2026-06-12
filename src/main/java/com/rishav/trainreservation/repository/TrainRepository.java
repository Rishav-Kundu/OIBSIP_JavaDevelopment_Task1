package com.rishav.trainreservation.repository;

import com.rishav.trainreservation.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository
        extends JpaRepository<Train, Long> {

    Train findByTrainNo(String trainNo);
}