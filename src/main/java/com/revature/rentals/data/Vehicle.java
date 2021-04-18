package com.revature.rentals.data;

public class Vehicle {
    private final String vin;
    private final String style;
    private final String model;
    private final String make;
    private final String image;

    private int mileage;
    private int dailyCost;

    public Vehicle(String vin, String style, String model, String make, String image, int mileage, int dailyCost) {
        this.vin = vin;
        this.style = style;
        this.model = model;
        this.make = make;
        this.image = image;
        this.mileage = mileage;
        this.dailyCost = dailyCost;
    }

    public String getVin() {
        return vin;
    }

    public String getStyle() {
        return style;
    }

    public String getModel() {
        return model;
    }

    public String getMake() {
        return make;
    }

    public String getImage() {
        return image;
    }

    public int getMileage() {
        return mileage;
    }

    public int getDailyCost() {
        return dailyCost;
    }
}