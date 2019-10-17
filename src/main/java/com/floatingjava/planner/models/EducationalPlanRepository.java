package com.floatingjava.planner.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface EducationalPlanRepository extends JpaRepository <EducationalPlan, Long> {
}
