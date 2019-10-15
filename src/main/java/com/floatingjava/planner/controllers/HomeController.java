package com.floatingjava.planner.controllers;

import com.floatingjava.planner.Data;
import com.floatingjava.planner.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    EducationalPlanRepository educationalPlanRepository;

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

    @GetMapping("/aboutUs")
    public String getAboutUs() {
        return "aboutUs";
    }

    @PostMapping("/generateEdPlan")
    public RedirectView generateEdPlan(Model m, String startPoint, String endPoint){
        //TODO: logic to make an EdPlan, and send it to thymeleaf as a string

        // Replace with logic!

        String edPlan = "This educational plan includes 201d50, 301d30, and Java-401d6";
        m.addAttribute("edPlan", edPlan);
        return new RedirectView("/");
    }

    @PostMapping("/saveEdPlan")
    public RedirectView saveEdPlan(String course101, String course102, String course201, String course301, String course401){
        //TODO: add inputs, make EdPlan from inputs, save EdPlan
        Long course101Id = Long.parseLong(course101);
        Long course102Id = Long.parseLong(course102);
        Long course201Id = Long.parseLong(course201);
        Long course301Id = Long.parseLong(course301);
        Long course401Id = Long.parseLong(course401);
        EducationalPlan educationalPlan = new EducationalPlan();
//        educationalPlanRepository.save();
        return new RedirectView("/");
    }
}