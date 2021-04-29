package com.revature.rentals.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Date;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @SequenceGenerator(name = "reservations_id_seq", sequenceName = "reservations_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservations_id_seq")
    private int id;

    @OneToOne
    @JoinColumn(name = "fk_customer")
    private Customer customer;
    @OneToOne
    @JoinColumn(name = "fk_location")
    private Provider provider;
    @OneToOne
    @JoinColumn(name = "fk_vehicle")
    private Vehicle vehicle;

    @Temporal(TemporalType.DATE)
    private Date pickup;
    @Temporal(TemporalType.DATE)
    private Date returned;

    private int safe_seats;
    private boolean gps;

    public Reservation() {}

    public Reservation(Customer customer, Provider provider, Vehicle vehicle, Date pickup, Date returned, int safe_seats, boolean gps) {
        this.customer = customer;
        this.provider = provider;
        this.vehicle = vehicle;
        this.pickup = pickup;
        this.returned = returned;
        this.safe_seats = safe_seats;
        this.gps = gps;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Provider getProvider() {
        return provider;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Date getPickup() {
        return pickup;
    }

    public Date getReturned() {
        return returned;
    }

    public int getSafeSeats() {
        return safe_seats;
    }

    public boolean isGps() {
        return gps;
    }
}