package com.floatingjava.planner.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

// Needs to be adjusted to add any new classes from the API not currently in the database

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "course101List", "course102List", "course201List", "course301List", "course401List"})
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    private String code;
    private String title;
    private String startDate;
    private String endDate;
    private String track;
    private String family;

    @OneToMany(mappedBy = "course101")
    List<EducationalPlan> course101List;
    @OneToMany(mappedBy = "course102")
    List<EducationalPlan> course102List;
    @OneToMany(mappedBy = "course201")
    List<EducationalPlan> course201List;
    @OneToMany(mappedBy = "course301")
    List<EducationalPlan> course301List;
    @OneToMany(mappedBy = "course401")
    List<EducationalPlan> course401List;

    public List<EducationalPlan> getCourse101List() {
        return course101List;
    }

    public List<EducationalPlan> getCourse102List() {
        return course102List;
    }

    public List<EducationalPlan> getCourse201List() {
        return course201List;
    }

    public List<EducationalPlan> getCourse301List() {
        return course301List;
    }

    public List<EducationalPlan> getCourse401List() {
        return course401List;
    }

    public Course() {}

    public Course (String code, String title, String startDate, String endDate, String track, String family) {
        this.code = code;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.track = track;
        this.family = family;
    }

    public String getCode() {
        return code;
    }

    // Does not seem to be used but site breaks when deleted
    public String getTitle() { return title; }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    // Does not seem to be used but site breaks when deleted
    public String getTrack() { return track; }

    // Does not seem to be used but site breaks when deleted
    public String getFamily() { return family; }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", track='" + track + '\'' +
                ", family='" + family + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }
}
