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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
        long id = 1;
        //checking if date exists, if not cat will make new date
        if(dateSavedRepository.findCountOfRows() == 0){
            DateSaved newDate = new DateSaved();
            dateSavedRepository.save(newDate);
        }

        if(courseRepository.findCountOfRows() == 0){
            Data data = new Data();
            try {
                ArrayList<Course> arrCourse = data.getCourseArray();
                courseRepository.saveAll(arrCourse);
            } catch (IOException f) {
                f.printStackTrace();
            }
        }

        DateSaved oldDate = dateSavedRepository.getOne(id);
        if ((System.currentTimeMillis() / 1000 / 60) > (oldDate.createdAtMinutes() + (long)(60 * 24))) {
            //updating created at in sql
            oldDate.setCreatedAtMinutes();
            dateSavedRepository.save(oldDate);

            //Getting new course data
            // This doesn't work if you have any saved EdPlans!
            // You should probably do an upsert here instead of entirely
            // replacing the course data you have.
            courseRepository.deleteAll();
            Data data = new Data();
            try {
                // pray to the java gods
                ArrayList<Course> arrCourse = data.getCourseArray();
                courseRepository.saveAll(arrCourse);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        List<Course> courses = courseRepository.findByStartDateGreaterThanEqualAndFamilyAndTrackOrderByStartDateAsc(LocalDateTime.now().toString(), "101", "day");
        m.addAttribute("courses",courses);
        return "index";
    }

    @GetMapping("/aboutUs")
    public String getAboutUs() {
        return "aboutUs";
    }


    // The fact that you have these two VERY similar methods makes me sad.
    // I'd prefer to (a) only send a single request from the frontend, and
    // (b) only do all of this database logic once.
    // You could do that by returning an object with both the string field and
    // the List<Course> field.
    // these variable names should be lowerCamel, not UpperCamel
    @GetMapping("/generateEdPlan/{startPoint}/{endPoint}")
    @ResponseBody
    public ApiResponse getCoursePath(Model m, @PathVariable String endPoint, @PathVariable long startPoint) throws ParseException {
        Course course = courseRepository.getOne(startPoint);
        String startDate = course.getStartDate();
        List<Course> sortedDay102 = courseRepository.findByStartDateGreaterThanEqualAndFamilyAndTrackOrderByStartDateAsc(startDate, "102", "day");

        String endDate102 = sortedDay102.get(0).getEndDate();
        List<Course> sortedDay201 = courseRepository.findByStartDateGreaterThanEqualAndFamilyAndTrackOrderByStartDateAsc(endDate102, "201", "day");

        String endDate201 = sortedDay201.get(0).getEndDate();
        List<Course> sortedDay301 = courseRepository.findByStartDateGreaterThanEqualAndFamilyAndTrackOrderByStartDateAsc(endDate201, "301", "day");

        String endDate301 = sortedDay301.get(0).getEndDate();
        List<Course> sortedDay401 = null;
        if(endPoint.equals("Fastest")){
            sortedDay401 = courseRepository.findByStartDateGreaterThanEqualAndFamilyContainingAndTrackOrderByStartDateAsc(endDate301, "401", "day");
        }else {
            // this logic is way simpler by using the string
            sortedDay401 = courseRepository.findByStartDateGreaterThanEqualAndFamilyAndTrackOrderByStartDateAsc(endDate301, String.format("%s-401", endPoint.toLowerCase()), "day");
        }

        List<Course> stuffToFront = sortedDay102;
        stuffToFront.addAll(sortedDay201);
        stuffToFront.addAll(sortedDay301);
        stuffToFront.addAll(sortedDay401);
        stuffToFront.add(course);

        // dry this up with a helper method...
        String day102 = listToString(sortedDay102);
        String day201 = listToString(sortedDay201);
        String day301 = listToString(sortedDay301);
        String day401 = listToString(sortedDay401);

        ApiResponse response = new ApiResponse();
        response.courses = stuffToFront;
        response.coursesString = String.format("The 101 course is %s. The 102 course is %s. The 201 course is %s. The 301 course is %s. The 401 course is %s.",
                course.getCode(), day102, day201, day301, day401);
        return response;
    }

    // Logically, this probably belongs in the SavedEdPlansController
    @PostMapping("/saveEdPlan")
    public RedirectView saveEdPlan(Principal p, String course101, String course102, String course201, String course301, String course401, String nameOfEdPlan){

        // more opportunity to dry this up...
        Course course101Course = getCourseByStringId(course101);
        Course course102Course = getCourseByStringId(course102);
        Course course201Course = getCourseByStringId(course201);
        Course course301Course = getCourseByStringId(course301);
        Course course401Course = getCourseByStringId(course401);
        ApplicationUser user = applicationUserRepository.findByUsername(p.getName());

        EducationalPlan educationalPlan = new EducationalPlan(user, course101Course, course102Course, course201Course, course301Course, course401Course, nameOfEdPlan);
        educationalPlanRepository.save(educationalPlan);

        return new RedirectView("/");
    }

    private Course getCourseByStringId(String id) {
        if(id != null){
            Long courseId = Long.parseLong(id);
            return courseRepository.getOne(courseId);
        } else {
            return null;
        }
    }

    private String listToString(List<Course> courses) {
        if(courses.size() == 0){
            return "unavailable";
        } else {
            return courses.get(0).getCode();
        }
    }

    @GetMapping("/calendarSource")
    @ResponseBody
    public List<Course> calendarSourceList(){
        return courseRepository.findAll();
    }
}

class ApiResponse {
    List<Course> courses;
    String coursesString;

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public String getCoursesString() {
        return coursesString;
    }

    public void setCoursesString(String coursesString) {
        this.coursesString = coursesString;
    }
}