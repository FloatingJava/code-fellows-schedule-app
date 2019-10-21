package com.floatingjava.planner.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    public List<Course> findAllByOrderByStartDateAsc();
    // Used for 101-301 courses
    public List<Course> findByStartDateGreaterThanEqualAndFamilyAndTrackOrderByStartDateAsc(String startDate, String family, String track);
    // Used for 401 courses
    public List<Course> findByStartDateGreaterThanEqualAndFamilyContainingAndTrackOrderByStartDateAsc(String startDate, String family, String track);
    @Query(value = "select count(*) from course", nativeQuery = true)
    public int findCountOfRows();
}
