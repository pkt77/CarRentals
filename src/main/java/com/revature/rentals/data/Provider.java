package com.revature.rentals.data;

public class Provider {
    private final int id;

    private final String company;
    private final String street;
    private final String city;
    private final String state;

    private final float lat;
    private final float longitude;

    public Provider(int id, String company, String street, String city, String state, float lat, float longitude) {
        this.id = id;
        this.company = company;
        this.street = street;
        this.city = city;
        this.state = state;
        this.lat = lat;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public String getCompany() {
        return company;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public float getLat() {
        return lat;
    }

    public float getLongitude() {
        return longitude;
    }
}