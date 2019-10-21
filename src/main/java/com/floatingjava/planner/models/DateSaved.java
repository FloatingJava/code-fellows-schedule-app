package com.floatingjava.planner.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Entity
public class DateSaved {

    //Stretch goal:  Have valid cache validation.
    //Issues we ran into that foreign key constraints if a user has a course saved, we can't drop the table.\
    //Do logic if no one has courses... or something... then we can delete.


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    public long createdAtMinutes() {
        return createdAtMinutes;
    }

    public long getId() {
        return id;
    }

    private long createdAtMinutes;

    public DateSaved() {
        this.createdAtMinutes = System.currentTimeMillis() / 1000 / 60;
    }

    public long getCreatedAtMinutes() {
        return createdAtMinutes;
    }

    public void setCreatedAtMinutes() {
        this.createdAtMinutes = System.currentTimeMillis() / 1000 / 60;
    }
}
