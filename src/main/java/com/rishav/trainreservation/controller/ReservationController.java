
package com.rishav.trainreservation.controller;

import com.rishav.trainreservation.entity.Reservation;
import com.rishav.trainreservation.entity.ReservationForm;
import com.rishav.trainreservation.entity.Train;
import com.rishav.trainreservation.entity.User;
import com.rishav.trainreservation.repository.ReservationRepository;
import com.rishav.trainreservation.repository.TrainRepository;
import com.rishav.trainreservation.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
public class ReservationController {

    private final TrainRepository trainRepository;
    private final ReservationRepository reservationRepository;
    private final UserService userService;

    public ReservationController(
            TrainRepository trainRepository,
            ReservationRepository reservationRepository,
            UserService userService) {

        this.trainRepository = trainRepository;
        this.reservationRepository = reservationRepository;
        this.userService = userService;
    }

    @GetMapping("/reserve/{trainId}")
    public String reservePage(
            @PathVariable Long trainId,
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam Double fare,
            Model model) {

        Train train =
                trainRepository.findById(trainId)
                        .orElseThrow();

        ReservationForm form =
                new ReservationForm();

        form.setFromStation(from);
        form.setToStation(to);
        form.setFare(fare);

        model.addAttribute("train", train);
        model.addAttribute("fare", fare);
        model.addAttribute("reservationForm", form);

        return "reserve-ticket";
    }

    @PostMapping("/reserve/{trainId}")
    public String saveReservation(
            @PathVariable Long trainId,
            @ModelAttribute ReservationForm reservationForm) {

        Train train =
                trainRepository.findById(trainId)
                        .orElseThrow();

        // Validation
        if (reservationForm.getPassengerCount() == null ||
                reservationForm.getPassengerCount() <= 0) {

            return "redirect:/";
        }

        if (reservationForm.getPassengerCount() >
                train.getAvailableSeats()) {

            return "redirect:/";
        }

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        User user =
                userService.findByEmail(
                        authentication.getName());

        Reservation reservation =
                new Reservation();

        reservation.setPnr(
                "PNR" + System.currentTimeMillis());

        reservation.setJourneyDate(
                reservationForm.getJourneyDate());

        reservation.setSource(
                reservationForm.getFromStation());

        reservation.setDestination(
                reservationForm.getToStation());

        reservation.setPassengerCount(
                reservationForm.getPassengerCount());

        double totalFare =
                reservationForm.getFare()
                        * reservationForm.getPassengerCount();

        reservation.setTotalFare(
                totalFare);

        reservation.setStatus(
                "CONFIRMED");

        reservation.setCreatedAt(
                LocalDateTime.now());

        reservation.setUser(user);

        reservation.setTrain(train);

        train.setAvailableSeats(
                train.getAvailableSeats()
                        - reservationForm.getPassengerCount());

        trainRepository.save(train);

        reservationRepository.save(
                reservation);

        return "redirect:/my-reservations";
    }

    @GetMapping("/my-reservations")
    public String myReservations(
            Model model) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        User user =
                userService.findByEmail(
                        authentication.getName());

        model.addAttribute(
                "reservations",
                reservationRepository.findByUser(user));

        return "my-reservations";
    }

    @GetMapping("/cancel-ticket")
    public String cancelTicketPage() {

        return "cancel-ticket";
    }

@PostMapping("/cancel-ticket/search")
public String searchPnr(
        @RequestParam String pnr,
        Model model) {

    Authentication authentication =
            SecurityContextHolder
                    .getContext()
                    .getAuthentication();

    User user =
            userService.findByEmail(
                    authentication.getName());

    Reservation reservation =
            reservationRepository.findByPnr(pnr);

    if (reservation == null) {

        model.addAttribute(
                "error",
                "No reservation found with the entered PNR.");

        return "cancel-ticket";
    }

    if (!reservation.getUser()
            .getId()
            .equals(user.getId())) {

        model.addAttribute(
                "error",
                "You are not authorized to access this reservation.");

        return "cancel-ticket";
    }

    model.addAttribute(
            "reservation",
            reservation);

    return "reservation-details";
}




@PostMapping("/cancel-ticket/confirm")
public String confirmCancellation(
        @RequestParam Long reservationId) {

    Authentication authentication =
            SecurityContextHolder
                    .getContext()
                    .getAuthentication();

    User user =
            userService.findByEmail(
                    authentication.getName());

    Reservation reservation =
            reservationRepository.findById(reservationId)
                    .orElseThrow();

    // Prevent cancelling another user's reservation
    if (!reservation.getUser()
            .getId()
            .equals(user.getId())) {

        return "redirect:/cancel-ticket";
    }

    if (!"CANCELLED".equals(
            reservation.getStatus())) {

        reservation.setStatus(
                "CANCELLED");

        Train train =
                reservation.getTrain();

        train.setAvailableSeats(
                train.getAvailableSeats()
                        + reservation.getPassengerCount());

        trainRepository.save(train);

        reservationRepository.save(
                reservation);
    }

    return "redirect:/my-reservations";
}

}


