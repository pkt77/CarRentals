package com.revature.rentals.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "providers")
public class Provider {
    @Id
    @SequenceGenerator(name = "providers_id_seq", sequenceName = "providers_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "providers_id_seq")
    private int id;

    private String company;
    private String street;
    private String city;
    private String state;

    private float lat;
    @Column(name = "long")
    private float longitude;

    public Provider() {}

    public Provider(int id) {
        this.id = id;
    }

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