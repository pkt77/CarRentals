package com.revature.rentals.repo;

import com.revature.rentals.data.Customer;
import com.revature.rentals.data.Provider;
import com.revature.rentals.data.Reservation;
import com.revature.rentals.data.Vehicle;

import java.io.Closeable;
import java.util.Collection;

public interface Repository extends Closeable {

    boolean createCustomer(Customer customer);

    Customer login(String username, String password);

    Vehicle getVehicle(String vin);

    Collection<Vehicle> getVehicles();

    Provider getProvider(int id);

    Collection<Provider> getProviders();

    void createReservation(Reservation reservation);
}