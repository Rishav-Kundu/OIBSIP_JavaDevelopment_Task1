
package com.rishav.trainreservation.controller;

import com.rishav.trainreservation.entity.SearchResult;
import com.rishav.trainreservation.entity.Train;
import com.rishav.trainreservation.entity.TrainRoute;
import com.rishav.trainreservation.repository.TrainRepository;
import com.rishav.trainreservation.repository.TrainRouteRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private final TrainRepository trainRepository;
    private final TrainRouteRepository trainRouteRepository;

    public HomeController(
            TrainRepository trainRepository,
            TrainRouteRepository trainRouteRepository) {

        this.trainRepository = trainRepository;
        this.trainRouteRepository = trainRouteRepository;
    }

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute(
                "trains",
                trainRepository.findAll());

        return "index";
    }

    @GetMapping("/search")
    public String searchTrains(
            @RequestParam String from,
            @RequestParam String to,
            Model model) {

        List<SearchResult> results =
                new ArrayList<>();

        for (Train train : trainRepository.findAll()) {

            TrainRoute fromRoute =
                    trainRouteRepository
                            .findByTrainAndStationName(
                                    train,
                                    from);

            TrainRoute toRoute =
                    trainRouteRepository
                            .findByTrainAndStationName(
                                    train,
                                    to);

            if (fromRoute == null ||
                    toRoute == null) {
                continue;
            }

            if (fromRoute.getStationOrder()
                    >= toRoute.getStationOrder()) {
                continue;
            }

            int stationsTravelled =
                    toRoute.getStationOrder()
                            - fromRoute.getStationOrder();

            double fare =
                    train.getBaseFare()
                            + (stationsTravelled
                            * train.getFarePerStation());

            results.add(
                    new SearchResult(
                            train,
                            from,
                            to,
                            fare));
        }

        model.addAttribute(
                "results",
                results);

        model.addAttribute(
                "from",
                from);

        model.addAttribute(
                "to",
                to);

        model.addAttribute(
                "trains",
                trainRepository.findAll());

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        if (authentication != null
                && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken)) {

            return "user-search";
        }

        return "index";
    }
}



