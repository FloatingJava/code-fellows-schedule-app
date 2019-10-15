package com.floatingjava.planner;

import com.floatingjava.planner.models.Course;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class Data {

    public String getCourses() throws IOException {
        // reading from the web
        URL getCourseUrl = new URL("https://s3-us-west-2.amazonaws.com/static.codefellows.org/courses/schedule.json");
        HttpURLConnection connection = (HttpURLConnection) getCourseUrl.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        // converting to buffer in to string
        StringBuilder data = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            data.append(line);
            line = reader.readLine();
        }
        return data.toString();
    }

    public ArrayList<Course> stringParser(String incomingJSON){
        ArrayList<Course> courses = new ArrayList<>();
        Gson gson = new Gson();

        //converting incoming data
        JsonObject incomingObj = gson.fromJson(incomingJSON, JsonObject.class);
        JsonArray incomingArr = incomingObj.get("courses").getAsJsonArray();

        // saving each element
        for(JsonElement e : incomingArr){
//            System.out.println("***************** new object ************");
//            System.out.println(gson.fromJson(e.getAsJsonObject().get("course"), Course.class));
            courses.add(gson.fromJson(e.getAsJsonObject().get("course"), Course.class));
        }

        return courses;
    }

    public ArrayList<Course> getCourseArray() throws IOException {
        String courseStringFromJSON = getCourses();
        ArrayList<Course> courses = stringParser(courseStringFromJSON);

        return courses;
    }

}
