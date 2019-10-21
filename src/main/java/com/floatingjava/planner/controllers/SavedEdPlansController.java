package com.floatingjava.planner.controllers;

import com.floatingjava.planner.models.ApplicationUser;
import com.floatingjava.planner.models.ApplicationUserRepository;
import com.floatingjava.planner.models.EducationalPlan;
import com.floatingjava.planner.models.EducationalPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

@Controller
public class SavedEdPlansController {

    @Autowired
    EducationalPlanRepository educationalPlanRepository;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @GetMapping("/myCourses")
    public String getMyEdPlans(Principal p, Model m) {

        ApplicationUser user = applicationUserRepository.findByUsername(p.getName());
        List<EducationalPlan> educationalPlans = user.getEducationalPlanList();
        m.addAttribute("edPlans", educationalPlans);
        return "myCourses";
    }

    //TODO detailed view of an ed plan, not done yet.
    //TODO add chart view for each ed plan
    @GetMapping("/myCourses/{edPlanId}")
    public String getSavedEdPlanDetails(Model m, @PathVariable long edPlanId) {
        m.addAttribute("edPlan", educationalPlanRepository.getOne(edPlanId));
        return "myCourses";
    }

    @PostMapping("/myCourses/delete")
    public RedirectView deleteTheAlbum(String planId) {
        Long planIdLong = Long.parseLong(planId);
        educationalPlanRepository.deleteById(planIdLong);
        return new RedirectView("/myCourses");
    }

}
