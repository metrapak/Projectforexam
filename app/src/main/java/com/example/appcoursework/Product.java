package com.example.appcoursework;

public class Product {
    private int id;
    private String name;
    private String energy;
    private String carbohydrates;
    private String proteins;
    private String fats;

    public Product(String name, String energy, String proteins,String fats, String carbohydrates , int id) {
        this.name = name;
        this.energy = energy;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates= carbohydrates;
        this.id = id;
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

    public String getEnergy() {
        return energy;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }
    public String getProteins() {
        return proteins;
    }

    public void setProteins(String proteins) {
        this.energy = proteins;
    }

    public String getFats() {
        return fats;
    }

    public void setFats(String fats) {
        this.fats = fats;
    }

    public String getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(String carbohydrates) {
        this.carbohydrates =carbohydrates;
    }
}
