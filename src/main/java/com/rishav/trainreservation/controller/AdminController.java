package com.rishav.trainreservation.controller;

import com.rishav.trainreservation.entity.Reservation;
import com.rishav.trainreservation.entity.Train;
import com.rishav.trainreservation.entity.TrainRoute;
import com.rishav.trainreservation.entity.User;
import com.rishav.trainreservation.repository.ReservationRepository;
import com.rishav.trainreservation.repository.TrainRepository;
import com.rishav.trainreservation.repository.TrainRouteRepository;
import com.rishav.trainreservation.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

private final TrainRepository trainRepository;
private final TrainRouteRepository trainRouteRepository;
private final UserRepository userRepository;
private final ReservationRepository reservationRepository;

public AdminController(
        TrainRepository trainRepository,
        TrainRouteRepository trainRouteRepository,
        UserRepository userRepository,
        ReservationRepository reservationRepository) {

    this.trainRepository = trainRepository;
    this.trainRouteRepository = trainRouteRepository;
    this.userRepository = userRepository;
    this.reservationRepository = reservationRepository;
}

@GetMapping("/dashboard")
public String dashboard(Model model) {

    model.addAttribute(
            "trainCount",
            trainRepository.count());

    model.addAttribute(
            "userCount",
            userRepository.count());

    model.addAttribute(
            "reservationCount",
            reservationRepository.count());

    return "admin-dashboard";
}

    @GetMapping("/trains")
    public String viewTrains(Model model) {

        model.addAttribute(
                "trains",
                trainRepository.findAll());

        return "admin-trains";
    }

    @GetMapping("/trains/add")
    public String showAddTrainForm(Model model) {

        model.addAttribute(
                "train",
                new Train());

        return "add-train";
    }

    @PostMapping("/trains/add")
    public String saveTrain(
            @ModelAttribute Train train) {

        trainRepository.save(train);

        return "redirect:/admin/trains";
    }

    @GetMapping("/trains/edit/{id}")
    public String editTrain(
            @PathVariable Long id,
            Model model) {

        Train train =
                trainRepository.findById(id)
                        .orElseThrow();

        model.addAttribute(
                "train",
                train);

        return "edit-train";
    }

    @PostMapping("/trains/update")
    public String updateTrain(
            @ModelAttribute Train train) {

        trainRepository.save(train);

        return "redirect:/admin/trains";
    }

    @GetMapping("/trains/delete/{id}")
    public String deleteTrain(
            @PathVariable Long id) {

        trainRepository.deleteById(id);

        return "redirect:/admin/trains";
    }

    @GetMapping("/trains/{id}/routes")
    public String manageRoutes(
            @PathVariable Long id,
            Model model) {

        Train train =
                trainRepository.findById(id)
                        .orElseThrow();

        model.addAttribute(
                "train",
                train);

        model.addAttribute(
                "routes",
                trainRouteRepository
                        .findByTrainOrderByStationOrderAsc(train));

        return "manage-routes";
    }

    @GetMapping("/trains/{id}/routes/add")
    public String showAddRouteForm(
            @PathVariable Long id,
            Model model) {

        Train train =
                trainRepository.findById(id)
                        .orElseThrow();

        model.addAttribute(
                "train",
                train);

        model.addAttribute(
                "route",
                new TrainRoute());

        return "add-route";
    }

    @PostMapping("/trains/{id}/routes/add")
    public String saveRoute(
            @PathVariable Long id,
            @ModelAttribute TrainRoute route) {

        Train train =
                trainRepository.findById(id)
                        .orElseThrow();

        long count =
                trainRouteRepository.countByTrain(train);

        if (count >= 6) {
            return "redirect:/admin/trains/" +
                    id +
                    "/routes";
        }

        TrainRoute newRoute = new TrainRoute();

        newRoute.setStationName(
                route.getStationName());

        newRoute.setArrivalTime(
                route.getArrivalTime());

        newRoute.setDepartureTime(
                route.getDepartureTime());

        newRoute.setStationOrder(
                route.getStationOrder());

        newRoute.setTrain(train);

        trainRouteRepository.save(newRoute);

        return "redirect:/admin/trains/" +
                id +
                "/routes";
    }

    @GetMapping("/routes/delete/{id}")
    public String deleteRoute(
            @PathVariable Long id) {

        TrainRoute route =
                trainRouteRepository.findById(id)
                        .orElseThrow();

        Long trainId =
                route.getTrain().getId();

        trainRouteRepository.delete(route);

        return "redirect:/admin/trains/" +
                trainId +
                "/routes";
    }

    @GetMapping("/users")
public String users(Model model) {

    model.addAttribute(
            "users",
            userRepository.findAll());

    return "admin-users";
}

@GetMapping("/reservations")
public String reservations(Model model) {

    model.addAttribute(
            "reservations",
            reservationRepository.findAll());

    return "admin-reservations";
}
}