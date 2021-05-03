package com.revature.rentals.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.rentals.data.Customer;
import com.revature.rentals.data.Provider;
import com.revature.rentals.data.Reservation;
import com.revature.rentals.data.Vehicle;
import com.revature.rentals.repo.Repository;
import org.simplejavamail.api.email.CalendarMethod;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.text.NumberFormat;
import java.util.concurrent.TimeUnit;

@WebServlet("/api/reservation")
public class ReservationServlet extends HttpServlet {
    private static final NumberFormat CURRENCY = NumberFormat.getCurrencyInstance();

    private static final double COST_PER_SEAT = 9.99;
    private static final double GPS_COST = 13.99;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("employee") == null) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        Repository repo = (Repository) req.getServletContext().getAttribute("repo");

        resp.getWriter().write(new ObjectMapper().writeValueAsString(repo.getValidReservations()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        Customer customer;

        if (session == null || (customer = (Customer) session.getAttribute("customer")) == null) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        try {
            int location = Integer.parseInt(req.getParameter("location"));
            String vin = req.getParameter("vin");
            Date pickup = Date.valueOf(req.getParameter("pickup"));
            Date returned = Date.valueOf(req.getParameter("returned"));
            int seats = Integer.parseInt(req.getParameter("seats"));
            boolean gps = Boolean.parseBoolean(req.getParameter("gps"));

            Repository repo = (Repository) req.getServletContext().getAttribute("repo");
            Provider provider = repo.getProvider(location);
            Vehicle vehicle = repo.getVehicle(vin);
            Reservation reservation = new Reservation(customer, provider, vehicle, pickup, returned, seats, gps);

            long days = TimeUnit.MILLISECONDS.toDays(returned.getTime() - pickup.getTime());
            double cost = (seats * COST_PER_SEAT) + (days * vehicle.getDailyCost()) + (gps ? GPS_COST : 0);

            repo.createReservation(reservation);

            Mailer mailer = (Mailer) req.getServletContext().getAttribute("mailer");

            if (mailer != null) {
                mailer.sendMail(EmailBuilder.startingBlank().from("Revature Car Rentals", mailer.getServerConfig().getUsername()).to(customer.getEmail()).withSubject("Car Rental Reservation Confirmation")
                        .withCalendarText(CalendarMethod.ADD, pickup.toString())
                        .withPlainText("Dear " + customer.getFirstName() + " " + customer.getLastName() + ",\n\n" +
                                "Thank you for your Revature Rental Car Reservation. We look forward to serving you.\n" +
                                "Please find below the details of your reservation.\n\n" +
                                "Confirmation Number: " + reservation.getId() + "\n\n" +
                                "Customer Information:\n" +
                                "Name: " + customer.getFirstName() + " " + customer.getLastName() + "\n" +
                                "Address: " + customer.getStreet() + " " + customer.getCity() + ", " + customer.getState() + ", " + customer.getPost() + "\n" +
                                "Phone: " + customer.getPhone() + "\n" +
                                "Email: " + customer.getEmail() + "\n\n" +
                                "Reservation Information:\n" +
                                "Vehicle: " + vehicle.getMake() + " " + vehicle.getModel() + "\n" +
                                "Pickup Location: " + provider.getStreet() + " " + provider.getCity() + ", " + provider.getState() + "\n" +
                                "Pickup Date: " + pickup + "\n" +
                                "Return Date: " + returned + "\n" +
                                "Number of Rental Days: " + days + "\n" +
                                "Estimated Rental Cost: " + CURRENCY.format(cost) + "\n\n" +
                                "Best regards,\n" +
                                "Revature Car Rentals").buildEmail());
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}