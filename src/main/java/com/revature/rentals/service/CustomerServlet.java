package com.revature.rentals.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.rentals.data.Customer;
import com.revature.rentals.data.Employee;
import com.revature.rentals.repo.Repository;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/api/customer/*")
public class CustomerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);

        if (session == null) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        Customer customer = (Customer) session.getAttribute("customer");

        if (customer != null) {
            resp.getWriter().write(new ObjectMapper().writeValueAsString(customer));
            return;
        }

        // Get all new customers if employee
        Employee employee = (Employee) session.getAttribute("employee");

        if (employee == null) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        Repository repo = (Repository) req.getServletContext().getAttribute("repo");

        resp.getWriter().write(new ObjectMapper().writeValueAsString(repo.getNewCustomers()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Repository repo = (Repository) req.getServletContext().getAttribute("repo");

        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String firstName = req.getParameter("first_name");
            String lastName = req.getParameter("last_name");
            Date dob = Date.valueOf(req.getParameter("dob"));
            String phone = req.getParameter("phone");
            String email = req.getParameter("email");
            String street = req.getParameter("street");
            String city = req.getParameter("city");
            String state = req.getParameter("state");
            int post = Integer.parseInt(req.getParameter("post"));
            String license = req.getParameter("license");
            String issued = req.getParameter("issued");
            Date expires = Date.valueOf(req.getParameter("expires"));

            Customer customer = new Customer(username, password, firstName, lastName, dob, phone, email, street, city, state, post, license, issued, expires);

            if (!repo.createCustomer(customer)) {
                resp.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        // Currently PUT will only enable customers, so no data is actually needed.
        HttpSession session = req.getSession(false);
        Employee employee;

        if (session == null || (employee = (Employee) session.getAttribute("employee")) == null) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String[] uri = req.getRequestURI().split("/");
        String username = uri[uri.length - 1];
        Repository repo = (Repository) req.getServletContext().getAttribute("repo");
        Customer customer = repo.getCustomer(username);

        if (customer == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        customer.setEnabled(true);
        repo.saveCustomer(customer);

        Mailer mailer = (Mailer) req.getServletContext().getAttribute("mailer");

        if (mailer != null) {
            mailer.sendMail(EmailBuilder.startingBlank().from("Revature Car Rentals", mailer.getServerConfig().getUsername()).to(customer.getEmail()).withSubject("Car Rental Account Confirmation")
                    .withPlainText("Dear " + customer.getFirstName() + ",\n\n" +
                            "Welcome to Revature Car Rentals. We are thrilled to be part of your vacation experience. Your account has been approved!\n" +
                            "Below is your new account confirmation information:\n\n" +
                            "User Name: " + customer.getUsername() + "\n" +
                            "Address: " + customer.getStreet() + " " + customer.getCity() + ", " + customer.getState() + ", " + customer.getPost() + "\n" +
                            "Phone: " + customer.getPhone() + "\n" +
                            "License No: " + customer.getLicense() + "\n\n" +
                            "Best regards,\n" +
                            "Revature Car Rentals").buildEmail());
        }
    }
}