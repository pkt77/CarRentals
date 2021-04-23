package com.revature.rentals.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.rentals.data.Customer;
import com.revature.rentals.repo.Repository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);

        if (session == null) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        Customer customer = (Customer) session.getAttribute("customer");

        resp.getWriter().write(new ObjectMapper().writeValueAsString(customer));
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

            if (repo.createCustomer(customer)) {
                req.getSession().setAttribute("customer", customer);
            } else {
                resp.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}