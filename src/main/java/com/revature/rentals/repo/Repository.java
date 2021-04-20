package com.revature.rentals.repo;

import com.revature.rentals.data.Customer;
import com.revature.rentals.data.Vehicle;

import java.io.Closeable;
import java.util.Collection;

public interface Repository extends Closeable {

    Customer login(String username, String password);

    Collection<Vehicle> getVehicles();
}