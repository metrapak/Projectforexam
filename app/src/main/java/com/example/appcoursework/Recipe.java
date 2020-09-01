package com.example.appcoursework;

public class Recipe {
    private int id;
    private String name;
    private String timeCooking;
    private String description;


    public Recipe(String name, String timeCooking,String description, int id) {
        this.name = name;
        this.timeCooking = timeCooking;
        this.id = id;
        this.description= description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimeCooking() {
        return timeCooking;
    }

    public void setTimeCooking(String timeCooking) {
        this.timeCooking = timeCooking;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description =description;
    }

}
