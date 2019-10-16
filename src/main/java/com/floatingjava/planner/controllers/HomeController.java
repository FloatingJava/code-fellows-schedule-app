package com.floatingjava.planner.controllers;

import com.floatingjava.planner.Data;
import com.floatingjava.planner.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.security.Principal;
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

    @Autowired
    DateSavedRepository dateSavedRepository;

    @GetMapping("/")
    public String getHome(Model m){
//        DateSaved saveDate = dateSavedRepository.save()
        long id = 1;
        try {
            DateSaved firstDate = dateSavedRepository.getOne(id);
//            System.out.println(firstDate);
        } catch (EntityNotFoundException e){
            System.out.println("*********************************************");
            System.out.println("Stack Trace Error");
            DateSaved newDate = new DateSaved();
            dateSavedRepository.save(newDate);
        }
        DateSaved oldDate = dateSavedRepository.getOne(id);
        if ((System.currentTimeMillis() / 1000 / 60) > (oldDate.createdAtMinutes() + (long)(60 * 24))) {
            System.out.println("*********************************************");
            System.out.println("Creating new time");
            oldDate.setCreatedAtMinutes();
            dateSavedRepository.save(oldDate);
        }
        List<Course> courses = courseRepository.findAllByOrderByStartDateAsc();
        m.addAttribute("courses",courses);
        return "index";
    }

//    @GetMapping("/query")
//    public String refreshDatabase() {
//        Data data = new Data();
//
//        try {
//            // pray to the java gods
//            ArrayList<Course> arrCourse = data.getCourseArray();
//            //uncomment to save...? okay.
////            courseRepository.saveAll(arrCourse);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    @GetMapping("/aboutUs")
    public String getAboutUs() {
        return "aboutUs";
    }

    @GetMapping("/generateEdPlan/{StartPoint}")
    @ResponseBody
    public List<Course> getCoursePath(Model m, String endPoint, @PathVariable long StartPoint) {
        Course course = courseRepository.getOne(StartPoint);
        String startDate = course.getStartDate();
        List<Course> sortedCourses = courseRepository.findByStartDateGreaterThanEqual(startDate);
        return sortedCourses;
    }

    @PostMapping("/saveEdPlan")
    public RedirectView saveEdPlan(Principal p, String course101, String course102, String course201, String course301, String course401, String nameOfEdPlan){
        //TODO: add inputs, make EdPlan from inputs, save EdPlan
        //Long id, ApplicationUser user, Course course101, Course course102, Course course201, Course course301, Course course401, String nameOfEdPlan
        Long course101Id = Long.parseLong(course101);
        Course course101Course = courseRepository.getOne(course101Id);
        Long course102Id = Long.parseLong(course102);
        Course course102Course = courseRepository.getOne(course102Id);
        Long course201Id = Long.parseLong(course201);
        Course course201Course = courseRepository.getOne(course201Id);
        Long course301Id = Long.parseLong(course301);
        Course course301Course = courseRepository.getOne(course301Id);
        Long course401Id = Long.parseLong(course401);
        Course course401Course = courseRepository.getOne(course401Id);

        ApplicationUser user = applicationUserRepository.findByUsername(p.getName());

        EducationalPlan educationalPlan = new EducationalPlan(user, course101Course, course102Course, course201Course, course301Course, course401Course, nameOfEdPlan);
        educationalPlanRepository.save(educationalPlan);

        return new RedirectView("/");
    }
}