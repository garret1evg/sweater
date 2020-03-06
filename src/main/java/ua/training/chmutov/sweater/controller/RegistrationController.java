package ua.training.chmutov.sweater.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ua.training.chmutov.sweater.domain.Role;
import ua.training.chmutov.sweater.domain.User;
import ua.training.chmutov.sweater.repos.UserRepo;


import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepo userRepo;
    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }
    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model){
        User userFromDb= userRepo.findByUsername(user.getUsername());
        if (userFromDb != null){
            model.put("message","User exists!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return "redirect:/login";
    }

}
