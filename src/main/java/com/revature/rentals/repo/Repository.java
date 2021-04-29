package com.revature.rentals.repo;

import com.revature.rentals.data.Customer;
import com.revature.rentals.data.Employee;
import com.revature.rentals.data.Provider;
import com.revature.rentals.data.Reservation;
import com.revature.rentals.data.Vehicle;

import java.io.Closeable;
import java.util.Collection;

public interface Repository extends Closeable {

    boolean createCustomer(Customer customer);

    Customer login(String username, String password);

    Employee loginEmployee(String username, String password);

    Customer getCustomer(String username);

    Collection<Customer> getNewCustomers();

    void saveCustomer(Customer customer);

    Vehicle getVehicle(String vin);

    Collection<Vehicle> getVehicles();

    Provider getProvider(int id);

    Collection<Provider> getProviders();

    void createReservation(Reservation reservation);

    Collection<Reservation> getValidReservations();
}