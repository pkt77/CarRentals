package com.revature.rentals.repo;

import com.revature.rentals.data.Customer;
import com.revature.rentals.data.Employee;
import com.revature.rentals.data.Provider;
import com.revature.rentals.data.Reservation;
import com.revature.rentals.data.Vehicle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
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
    public boolean createCustomer(Customer customer) {
        try (Session session = sessions.openSession()) {
            Transaction tran = session.beginTransaction();

            session.save(customer);
            tran.commit();
        } catch (PersistenceException e) {
            return false;
        }
        return true;
    }

    @Override
    public Customer login(String username, String password) {
        Session session = sessions.openSession();
        Customer customer = session.get(Customer.class, username);

        session.close();

        return customer != null && customer.correctPassword(password) ? customer : null;
    }

    @Override
    public Employee loginEmployee(String username, String password) {
        Session session = sessions.openSession();
        Employee employee = session.get(Employee.class, username);

        session.close();

        return employee != null && employee.correctPassword(password) ? employee : null;
    }

    @Override
    public Customer getCustomer(String username) {
        Session session = sessions.openSession();
        Customer customer = session.get(Customer.class, username);

        session.close();

        return customer;
    }

    @Override
    public Collection<Customer> getNewCustomers() {
        Session session = sessions.openSession();
        Collection<Customer> newCustomers = session.createNativeQuery("SELECT * FROM customers WHERE enabled=false", Customer.class).list();

        session.close();
        return newCustomers;
    }

    @Override
    public void saveCustomer(Customer customer) {
        Session session = sessions.openSession();
        Transaction transaction = session.beginTransaction();

        session.update(customer);
        transaction.commit();
        session.close();
    }

    @Override
    public Vehicle getVehicle(String vin) {
        Session session = sessions.openSession();
        Vehicle vehicle = session.get(Vehicle.class, vin);

        session.close();

        return vehicle;
    }

    @Override
    public Collection<Vehicle> getVehicles() {
        Session session = sessions.openSession();
        Collection<Vehicle> vehicles = session.createNativeQuery("SELECT * FROM vehicles", Vehicle.class).list();

        session.close();
        return vehicles;
    }

    @Override
    public Provider getProvider(int id) {
        Session session = sessions.openSession();
        Provider provider = session.get(Provider.class, id);

        session.close();

        return provider;
    }

    @Override
    public Collection<Provider> getProviders() {
        Session session = sessions.openSession();
        Collection<Provider> providers = session.createNativeQuery("SELECT * FROM providers", Provider.class).list();

        session.close();
        return providers;
    }

    @Override
    public void createReservation(Reservation reservation) {
        try (Session session = sessions.openSession()) {
            Transaction tran = session.beginTransaction();

            session.persist(reservation);
            tran.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Reservation> getValidReservations() {
        Session session = sessions.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Reservation> query = builder.createQuery(Reservation.class);
        Root<Reservation> root = query.from(Reservation.class);

        query.select(root).where(builder.greaterThanOrEqualTo(root.get("returned"), LocalDate.now()));

        Collection<Reservation> reservations = session.createQuery(query).getResultList();

        session.close();
        return reservations;
    }

    @Override
    public void close() {
        sessions.close();
    }
}