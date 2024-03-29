package com.floatingjava.planner.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    public List<Course> findAllByOrderByStartDateAsc();
    public List<Course> findByStartDateGreaterThanEqualAndFamilyAndTrackOrderByStartDateAsc(String startDate, String family, String track);
    public List<Course> findByStartDateGreaterThanEqualAndFamilyContainingAndTrackOrderByStartDateAsc(String startDate, String family, String track);
    @Query(value = "select count(*) from course", nativeQuery = true)
    public int findCountOfRows();
}
