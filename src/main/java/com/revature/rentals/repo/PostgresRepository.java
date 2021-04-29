package com.revature.rentals.repo;

import com.revature.rentals.data.Customer;
import com.revature.rentals.data.Employee;
import com.revature.rentals.data.Provider;
import com.revature.rentals.data.Reservation;
import com.revature.rentals.data.Vehicle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class PostgresRepository implements Repository {
    private final Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public PostgresRepository(String url, String username, String password) throws SQLException {
        connection = DriverManager.getConnection("jdbc:postgresql://" + url, username, password);
    }

    @Override
    public boolean createCustomer(Customer customer) {
        return false;
    }

    @Override
    public Customer login(String username, String password) {
        try (PreparedStatement select = connection.prepareStatement("SELECT password_hash, salt FROM customers WHERE username = ?")) {
            select.setString(1, username);

            ResultSet result = select.executeQuery();

            if (!result.next()) {
                return null;
            }

        /*    Customer customer = new Customer(username, result.getBytes(1), result.getBytes(2));

            if (customer.correctPassword(password)) {

                return customer;
            }*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Employee loginEmployee(String username, String password) {
        return null;
    }

    @Override
    public Customer getCustomer(String username) {
        return null;
    }

    @Override
    public Collection<Customer> getNewCustomers() {
        return null;
    }

    @Override
    public void saveCustomer(Customer customer) {

    }

    @Override
    public Vehicle getVehicle(String vin) {
        return null;
    }

    @Override
    public Collection<Vehicle> getVehicles() {
        Collection<Vehicle> vehicles = new ArrayList<>();

        try (PreparedStatement get = connection.prepareStatement("SELECT * FROM vehicles")) {
            ResultSet result = get.executeQuery();

            while (result.next()) {
                vehicles.add(new Vehicle(result.getString(1), result.getString(3), result.getString(4),
                        result.getString(5), result.getString(7), result.getInt(2), result.getInt(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    @Override
    public Provider getProvider(int id) {
        return null;
    }

    @Override
    public Collection<Provider> getProviders() {
        Collection<Provider> providers = new ArrayList<>();

        try (PreparedStatement get = connection.prepareStatement("SELECT * FROM providers")) {
            ResultSet result = get.executeQuery();

            while (result.next()) {
                providers.add(new Provider(result.getInt(1), result.getString(2), result.getString(3), result.getString(4),
                        result.getString(5), result.getFloat(6), result.getFloat(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return providers;
    }

    @Override
    public void createReservation(Reservation reservation) {}

    @Override
    public Collection<Reservation> getValidReservations() {
        return null;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}