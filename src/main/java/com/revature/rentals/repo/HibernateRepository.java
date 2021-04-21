package com.revature.rentals.repo;

import com.revature.rentals.data.Customer;
import com.revature.rentals.data.Provider;
import com.revature.rentals.data.Vehicle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Collection;

public class HibernateRepository implements Repository {
    private final SessionFactory sessions;

    public HibernateRepository(String url, String username, String password) {
        sessions = new Configuration().configure("hibernate.cfg.xml")
                .setProperty("hibernate.connection.url", "jdbc:postgresql://" + url)
                .setProperty("hibernate.connection.username", username)
                .setProperty("hibernate.connection.password", password)
                .buildSessionFactory();
    }

    @Override
    public Customer login(String username, String password) {
        Session session = sessions.openSession();
        Customer customer = session.get(Customer.class, username);

        session.close();

        return customer != null && customer.correctPassword(password) ? customer : null;
    }

    @Override
    public Collection<Vehicle> getVehicles() {
        Session session = sessions.openSession();
        Collection<Vehicle> vehicles = session.createNativeQuery("SELECT * FROM vehicles", Vehicle.class).list();

        session.close();
        return vehicles;
    }

    @Override
    public Collection<Provider> getProviders() {
        Session session = sessions.openSession();
        Collection<Provider> providers = session.createNativeQuery("SELECT * FROM providers", Provider.class).list();

        session.close();
        return providers;
    }

    @Override
    public void close() {
        sessions.close();
    }
}