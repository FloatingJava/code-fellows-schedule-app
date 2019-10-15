package com.floatingjava.planner.models;

import javax.persistence.*;

@Entity
public class EducationalPlan {
    //This ties the family together.
    //Need courses for 101-401
    //    Course courseCode;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    ApplicationUser user;

    @ManyToOne
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





}

