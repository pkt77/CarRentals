package com.revature.rentals.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Date;

@Entity
@Table(name = "customers")
public class Customer extends User {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Temporal(TemporalType.DATE)
    private Date dob;

    private String phone;
    private String email;
    private String street;
    private String city;
    private String state;
    private int post;

    private String license;
    private String issued;
    @Temporal(TemporalType.DATE)
    private Date expires;

    @JsonIgnore
    private boolean enabled;

    public Customer() {}

    public Customer(String username, String password, String firstName, String lastName, Date dob, String phone, String email, String street,
                    String city, String state, int post, String license, String issued, Date expires) {
        super(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.phone = phone;
        this.email = email;
        this.street = street;
        this.city = city;
        this.state = state;
        this.post = post;
        this.license = license;
        this.issued = issued;
        this.expires = expires;
    }

    @Override
    public String toString() {
        return "Customer{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", post=" + post +
                ", license='" + license + '\'' +
                ", issued='" + issued + '\'' +
                ", expires=" + expires +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDob() {
        return dob;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
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

    public int getPost() {
        return post;
    }

    public String getLicense() {
        return license;
    }

    public String getIssued() {
        return issued;
    }

    public Date getExpires() {
        return expires;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}