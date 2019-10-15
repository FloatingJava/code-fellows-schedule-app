package com.floatingjava.planner.controllers;

import com.floatingjava.planner.models.Course;
import com.floatingjava.planner.models.CourseRepository;
import com.floatingjava.planner.models.EducationalPlan;
import com.floatingjava.planner.models.EducationalPlanRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

public class SavedEdPlansController {

    @GetMapping("/myCourses/{edPlanId}")
    public String getAlbumDetails(Model m, @PathVariable long edPlanId){
        List<EducationalPlan> educationalPlans = EducationalPlanRepository.findAll();
        m.addAttribute("edPlan", EducationalPlanRepository.getOne(edPlanId));
        return "myCourses";
    }

    @PostMapping("/myCourses/delete")
    public RedirectView deleteTheAlbum(long id){
        EducationalPlanRepository.deleteById(id);
        return new RedirectView("/myCourses");
    }


}
