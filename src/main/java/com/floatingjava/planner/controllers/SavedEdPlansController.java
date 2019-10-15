package com.floatingjava.planner.controllers;

import com.floatingjava.planner.models.EducationalPlan;
import com.floatingjava.planner.models.EducationalPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

public class SavedEdPlansController {

    @Autowired
    EducationalPlanRepository educationalPlanRepository;

    @GetMapping("/myCourses")
    public String getMyEdPlans(Model m) {
        List<EducationalPlan> educationalPlans = educationalPlanRepository.findAll();
        m.addAttribute("edPlans", educationalPlans);
        return "myCourses";
    }

    @GetMapping("/myCourses/{edPlanId}")
    public String getSavedEdPlanDetails(Model m, @PathVariable long edPlanId) {
        m.addAttribute("edPlan", educationalPlanRepository.getOne(edPlanId));
        return "myCourses";
    }

    @PostMapping("/myCourses/delete")
    public RedirectView deleteTheAlbum(long id) {
        educationalPlanRepository.deleteById(id);
        return new RedirectView("/myCourses");
    }

}
