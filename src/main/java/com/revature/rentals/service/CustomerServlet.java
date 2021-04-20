package com.revature.rentals.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.rentals.data.Customer;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/customer/*")
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
}