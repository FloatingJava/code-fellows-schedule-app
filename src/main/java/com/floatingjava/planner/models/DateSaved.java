package com.floatingjava.planner.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Entity
public class DateSaved {

    // Creates a timestamp for when the API was last downloaded to the database
    //createdAt tracks down to the minute, not seconds or milliseconds
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
