package com.floatingjava.planner.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    public List<Course> findAllByOrderByStartDateAsc();
    public List<Course> findByStartDateGreaterThanEqualAndFamilyAndTrackOrderByStartDateAsc(String startDate, String family, String track);
    //In English... Find the start date that is greater than or equal to inputted date and family is contains inputted values family and track
    //contains inputted value, then sort it by starting date ascended
    //select * from course where start_date >= ?1 and family like ?2 and track like ?3 order by start_date asc limit 1
    public List<Course> findByStartDateGreaterThanEqualAndFamilyContainingAndTrackOrderByStartDateAsc(String startDate, String family, String track);
    @Query(value = "select count(*) from course", nativeQuery = true)
    public int findCountOfRows();
}
