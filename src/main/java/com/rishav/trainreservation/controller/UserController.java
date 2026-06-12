
package com.rishav.trainreservation.controller;

import com.rishav.trainreservation.entity.User;
import com.rishav.trainreservation.repository.TrainRepository;
import com.rishav.trainreservation.repository.UserRepository;
import com.rishav.trainreservation.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TrainRepository trainRepository;

    public UserController(
            UserService userService,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            TrainRepository trainRepository) {

        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.trainRepository = trainRepository;
    }


@GetMapping("/dashboard")
public String dashboard(Model model) {

    Authentication authentication =
            SecurityContextHolder
                    .getContext()
                    .getAuthentication();

    User user =
            userService.findByEmail(
                    authentication.getName());

    model.addAttribute(
            "user",
            user);

    return "user-dashboard";
}



    @GetMapping("/user/search")
    public String userSearch(Model model) {

        model.addAttribute(
                "trains",
                trainRepository.findAll());

        return "user-search";
    }
    

    @GetMapping("/profile")
    public String profile(Model model) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        User user =
                userService.findByEmail(
                        authentication.getName());

        model.addAttribute(
                "user",
                user);

        return "profile";
    }

   @PostMapping("/profile/update")
public String updateProfile(
        @ModelAttribute User updatedUser) {

    Authentication authentication =
            SecurityContextHolder
                    .getContext()
                    .getAuthentication();

    User user =
            userService.findByEmail(
                    authentication.getName());

    user.setName(
            updatedUser.getName());

    user.setPhone(
            updatedUser.getPhone());

    if (updatedUser.getPassword() != null &&
            !updatedUser.getPassword().isBlank()) {

        user.setPassword(
                passwordEncoder.encode(
                        updatedUser.getPassword()));
    }

    userRepository.save(user);

    return "redirect:/profile?success";
}
}



