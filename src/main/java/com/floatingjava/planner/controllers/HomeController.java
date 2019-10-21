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
        //Part of this is unimplemented cache validation.
        //Currently this just checks if the database is empty, if it is empty then save new data, else just fetch existing.
        long id = 1;
        //checking if date exists, if not it will make new date
        //Probably a good thing to throw this into a method.
        if(dateSavedRepository.findCountOfRows() == 0){
            //If nothing is in the date_saved table, save new date
            DateSaved newDate = new DateSaved();
            dateSavedRepository.save(newDate);
        }

        if(courseRepository.findCountOfRows() == 0){
            //If nothing is in the course table, save new courses
            Data data = new Data();
            try {
                //This calls on the Data class which calls the API
                ArrayList<Course> arrCourse = data.getCourseArray();
                //Saved it to the database
                courseRepository.saveAll(arrCourse);
            } catch (IOException f) {
                f.printStackTrace();
            }
        }

        //More unused cache validation
        DateSaved oldDate = dateSavedRepository.getOne(id);
        if ((System.currentTimeMillis() / 1000 / 60) > (oldDate.createdAtMinutes() + (long)(60 * 24))) {
            //updating created at in sql
            oldDate.setCreatedAtMinutes();
            dateSavedRepository.save(oldDate);

            //Getting new course data
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
        //This finds a 101 that is greater than or equal to todays date and sends that to the front page
        //So you'll never have a 101 that's older than the current
        List<Course> courses = courseRepository.findByStartDateGreaterThanEqualAndFamilyAndTrackOrderByStartDateAsc(LocalDateTime.now().toString(), "101", "day");
        m.addAttribute("courses",courses);
        return "index";
    }

    @GetMapping("/aboutUs")
    public String getAboutUs() {
        return "aboutUs";
    }

    @GetMapping("/generateEdPlanString/{StartPoint}/{EndPoint}")
    @ResponseBody
    public String getCoursePathString(Model m,@PathVariable String EndPoint, @PathVariable long StartPoint) throws ParseException {
        //StartPoint is always a 101 in this version.
        //So this finds the date from SQL then steps through each gross JPA logic to find the next
        //course in the list based on the previous start date.

        Course course = courseRepository.getOne(StartPoint);
        String startDate = course.getStartDate();
        List<Course> sortedDay102 = courseRepository.findByStartDateGreaterThanEqualAndFamilyAndTrackOrderByStartDateAsc(startDate, "102", "day");

        String endDate102 = sortedDay102.get(0).getEndDate();
        List<Course> sortedDay201 = courseRepository.findByStartDateGreaterThanEqualAndFamilyAndTrackOrderByStartDateAsc(endDate102, "201", "day");

        String endDate201 = sortedDay201.get(0).getEndDate();
        List<Course> sortedDay301 = courseRepository.findByStartDateGreaterThanEqualAndFamilyAndTrackOrderByStartDateAsc(endDate201, "301", "day");

        String endDate301 = sortedDay301.get(0).getEndDate();

        //Error handling if a 401 is null.  If it is null, SQL can just save it as a nullable thing.
        //Else we do some logic below and grab the appropriate 401.
        List<Course> sortedDay401 = null;
        if(EndPoint.equals("Fastest")){
            sortedDay401 = courseRepository.findByStartDateGreaterThanEqualAndFamilyContainingAndTrackOrderByStartDateAsc(endDate301, "401", "day");
        }else if(EndPoint.equals("Java")){
            sortedDay401 = courseRepository.findByStartDateGreaterThanEqualAndFamilyAndTrackOrderByStartDateAsc(endDate301, "java-401", "day");
        }else if(EndPoint.equals("dotnet")){
            sortedDay401 = courseRepository.findByStartDateGreaterThanEqualAndFamilyAndTrackOrderByStartDateAsc(endDate301, "dotnet-401", "day");
        }else if(EndPoint.equals("Python")){
            sortedDay401 = courseRepository.findByStartDateGreaterThanEqualAndFamilyAndTrackOrderByStartDateAsc(endDate301, "python-401", "day");
        }else if(EndPoint.equals("Javascript")){
            sortedDay401 = courseRepository.findByStartDateGreaterThanEqualAndFamilyAndTrackOrderByStartDateAsc(endDate301, "javascript-401", "day");
        }


        //This creates a string to be passed to the front.  If there is no course available, it will set the course title to
        //"unavailable"
        String day102 = "";
        if(sortedDay102.size() == 0){
            day102 += "unavailable";
        } else {
            day102 += sortedDay102.get(0).getCode();
        }

        String day201 = "";
        if(sortedDay201.size() == 0){
            day201 += "unavailable";
        } else {
            day201 += sortedDay201.get(0).getCode();
        }

        String day301 = "";
        if(sortedDay301.size() == 0){
            day301 += "unavailable";
        } else {
            day301 += sortedDay301.get(0).getCode();
        }

        String day401 = "";
        if(sortedDay401.size() == 0){
            day401 += "unavailable";
        } else {
            day401 += sortedDay401.get(0).getCode();
        }

        String returnString = String.format("The 101 course is; %s. The 102 course is %s. The 201 course is %s. The 301 course is %s. This 401 course is %s",
                course.getCode(), day102, day201, day301, day401);
        System.out.println(returnString);


        return returnString;
    }

    @GetMapping("/generateEdPlan/{StartPoint}/{EndPoint}")
    @ResponseBody
    public List<Course> getCoursePath(Model m,@PathVariable String EndPoint, @PathVariable long StartPoint) throws ParseException {
        Course course = courseRepository.getOne(StartPoint);
        String startDate = course.getStartDate();
        List<Course> sortedDay102 = courseRepository.findByStartDateGreaterThanEqualAndFamilyAndTrackOrderByStartDateAsc(startDate, "102", "day");

        String endDate102 = sortedDay102.get(0).getEndDate();
        List<Course> sortedDay201 = courseRepository.findByStartDateGreaterThanEqualAndFamilyAndTrackOrderByStartDateAsc(endDate102, "201", "day");

        String endDate201 = sortedDay201.get(0).getEndDate();
        List<Course> sortedDay301 = courseRepository.findByStartDateGreaterThanEqualAndFamilyAndTrackOrderByStartDateAsc(endDate201, "301", "day");

        String endDate301 = sortedDay301.get(0).getEndDate();
        List<Course> sortedDay401 = null;
        if(EndPoint.equals("Fastest")){
            sortedDay401 = courseRepository.findByStartDateGreaterThanEqualAndFamilyContainingAndTrackOrderByStartDateAsc(endDate301, "401", "day");
        }else if(EndPoint.equals("Java")){
            sortedDay401 = courseRepository.findByStartDateGreaterThanEqualAndFamilyAndTrackOrderByStartDateAsc(endDate301, "java-401", "day");
        }else if(EndPoint.equals("dotnet")){
            sortedDay401 = courseRepository.findByStartDateGreaterThanEqualAndFamilyAndTrackOrderByStartDateAsc(endDate301, "dotnet-401", "day");
        }else if(EndPoint.equals("Python")){
            sortedDay401 = courseRepository.findByStartDateGreaterThanEqualAndFamilyAndTrackOrderByStartDateAsc(endDate301, "python-401", "day");
        }else if(EndPoint.equals("Javascript")){
            sortedDay401 = courseRepository.findByStartDateGreaterThanEqualAndFamilyAndTrackOrderByStartDateAsc(endDate301, "javascript-401", "day");
        }

        List<Course> stuffToFront = sortedDay102;
        stuffToFront.addAll(sortedDay201);
        stuffToFront.addAll(sortedDay301);
        stuffToFront.addAll(sortedDay401);
        stuffToFront.add(course);
        return stuffToFront;
    }

    @PostMapping("/saveEdPlan")
    public RedirectView saveEdPlan(Principal p, String course101, String course102, String course201, String course301, String course401, String nameOfEdPlan){

        //Initially sets the course value to null, then if the value is not null set it.
        Course course101Course = null;
        //Sets the course101 ID to the drop down ID if not null.
        if(course101 != null){
            Long course101Id = Long.parseLong(course101);
            course101Course = courseRepository.getOne(course101Id);
        }

        Course course102Course = null;
        if(course102 != null){
            Long course102Id = Long.parseLong(course102);
            course102Course = courseRepository.getOne(course102Id);
        }

        Course course201Course = null;
        if(course201 != null){
            Long course201Id = Long.parseLong(course201);
            course201Course = courseRepository.getOne(course201Id);
        }

        Course course301Course = null;
        if(course301 != null){
            Long course301Id = Long.parseLong(course301);
            course301Course = courseRepository.getOne(course301Id);
        }
        Course course401Course = null;
        if(course401 != null) {
            Long course401Id = Long.parseLong(course401);
            course401Course = courseRepository.getOne(course401Id);
        }

        ApplicationUser user = applicationUserRepository.findByUsername(p.getName());

        EducationalPlan educationalPlan = new EducationalPlan(user, course101Course, course102Course, course201Course, course301Course, course401Course, nameOfEdPlan);
        educationalPlanRepository.save(educationalPlan);

        return new RedirectView("/");
    }

    @GetMapping("/calendarSource")
    @ResponseBody
    public List<Course> calendarSourceList(){
        return courseRepository.findAll();
    }
}
