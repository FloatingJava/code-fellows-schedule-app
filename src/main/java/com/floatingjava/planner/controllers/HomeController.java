package com.floatingjava.planner.controllers;

import com.floatingjava.planner.Data;
import com.floatingjava.planner.models.Course;
import com.floatingjava.planner.models.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;

@Controller
public class HomeController {

    @Autowired
    CourseRepository courseRepository;

    @GetMapping("/")
    public String getHome(Principal p, Model m){
        if(p != null){
            m.addAttribute("username", p.getName());
        }
        Data data = new Data();

        try {
            // for to the java gods
            ArrayList<Course> arrCourse = data.getCourseArray();
            //uncomment to save..? okay.
//            courseRepository.saveAll(arrCourse);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "index";
    }

    /*
    Comment:  Hiiiiiiiiii
     */

}