package com.floatingjava.planner.controllers;

import com.floatingjava.planner.models.ApplicationUserRepository;
import com.floatingjava.planner.models.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import java.util.ArrayList;

@Controller
public class ApplicationUserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @PostMapping("/signup")
    public RedirectView createNewUser(String username, String password, String nameFirst, String nameLast){

        ApplicationUser newUser = new ApplicationUser(username, passwordEncoder.encode(password), nameFirst, nameLast);

        applicationUserRepository.save(newUser);

        // maybe autologin?
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new RedirectView("/");
    }

    @GetMapping("/signUp")
    public String getSignUp(){
        return "signUp";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping("/logout")
    public RedirectView logout(){

        return new RedirectView("/");
    }
}