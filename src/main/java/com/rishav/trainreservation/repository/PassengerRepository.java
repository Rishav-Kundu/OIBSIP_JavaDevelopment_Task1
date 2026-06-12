package com.rishav.trainreservation.repository;

import com.rishav.trainreservation.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository
        extends JpaRepository<Passenger, Long> {
}