package com.revature.rentals.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    private String vin;
    private String style;
    private String model;
    private String make;
    private String image;

    private int mileage;
    @Column(name = "daily_cost")
    private int dailyCost;

    public Vehicle() {}

    public Vehicle(String vin) {
        this.vin = vin;
    }

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