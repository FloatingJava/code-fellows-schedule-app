package com.floatingjava.planner.models;

import javax.persistence.*;

@Entity
public class EducationalPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    ApplicationUser user;

    @ManyToOne()
    Course course101;
    @ManyToOne
    Course course102;
    @ManyToOne
    Course course201;
    @ManyToOne
    Course course301;
    @ManyToOne
    Course course401;

    public String nameOfEdPlan;

    public EducationalPlan() {};

    public EducationalPlan(ApplicationUser user, Course course101, Course course102, Course course201, Course course301, Course course401, String nameOfEdPlan) {
        this.user = user;
        this.course101 = course101;
        this.course102 = course102;
        this.course201 = course201;
        this.course301 = course301;
        this.course401 = course401;
        this.nameOfEdPlan = nameOfEdPlan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Does not seem to be used but site breaks when deleted
    public ApplicationUser getUser() { return user; }

    // Does not seem to be used but site breaks when deleted
    public void setUser(ApplicationUser user) { this.user = user; }

    // Does not seem to be used but site breaks when deleted
    public Course getCourse101() { return course101; }

    // Does not seem to be used but site breaks when deleted
    public void setCourse101(Course course101) { this.course101 = course101; }

    // Does not seem to be used but site breaks when deleted
    public Course getCourse102() { return course102; }

    // Does not seem to be used but site breaks when deleted
    public void setCourse102(Course course102) { this.course102 = course102; }

    // Does not seem to be used but site breaks when deleted
    public Course getCourse201() { return course201; }

    // Does not seem to be used but site breaks when deleted
    public void setCourse201(Course course201) { this.course201 = course201; }

    // Does not seem to be used but site breaks when deleted
    public Course getCourse301() { return course301; }

    // Does not seem to be used but site breaks when deleted
    public void setCourse301(Course course301) { this.course301 = course301; }

    // Does not seem to be used but site breaks when deleted
    public Course getCourse401() { return course401; }

    // Does not seem to be used but site breaks when deleted
    public void setCourse401(Course course401) { this.course401 = course401; }

    // Does not seem to be used but site breaks when deleted
    public String getNameOfEdPlan() { return nameOfEdPlan; }

    // Does not seem to be used but site breaks when deleted
    public void setNameOfEdPlan(String nameOfEdPlan) { this.nameOfEdPlan = nameOfEdPlan; }

    @Override
    public String toString() {
        return "EducationalPlan{" +
                "id=" + id +
                ", user=" + user +
                ", course101=" + course101 +
                ", course102=" + course102 +
                ", course201=" + course201 +
                ", course301=" + course301 +
                ", course401=" + course401 +
                ", nameOfEdPlan='" + nameOfEdPlan + '\'' +
                '}';
    }
}

