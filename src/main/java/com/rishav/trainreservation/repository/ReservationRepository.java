package com.rishav.trainreservation.repository;

import com.rishav.trainreservation.entity.Reservation;
import com.rishav.trainreservation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByUser(User user);

    Reservation findByPnr(String pnr);

}
