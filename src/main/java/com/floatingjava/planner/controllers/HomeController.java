package com.floatingjava.planner.controllers;

import com.floatingjava.planner.Data;
import com.floatingjava.planner.models.Course;
import com.floatingjava.planner.models.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CourseRepository courseRepository;

    @GetMapping("/")
    public String getHome(Model m){

        Data data = new Data();

        try {
            // pray to the java gods
            ArrayList<Course> arrCourse = data.getCourseArray();
            //uncomment to save...? okay.
//            courseRepository.saveAll(arrCourse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Course> courses = courseRepository.findAll();
        m.addAttribute("courses",courses);
        return "index";
    }

    @PostMapping("/generateEdPlan")
    public RedirectView generateEdPlan(Model m, String startingCourse, String chosenEdPlan){
        //TODO: logic to make an EdPlan, and send it to thymeleaf as a string
        String edPlan = "201d50, then 301d30, and 401d6Java or stuff";
        m.addAttribute("edPlan", edPlan);
        return new RedirectView("/");
    }

    @PostMapping("/saveEdPlan")
    public RedirectView saveEdPlan(Model m){
        //TODO: add inputs, make EdPlan from inputs, save EdPlan
        return new RedirectView("/");
    }
}