package com.revature.rentals.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.rentals.data.Customer;
import com.revature.rentals.data.Employee;
import com.revature.rentals.data.User;
import com.revature.rentals.repo.Repository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/api/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);

        if (session == null) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        User user = (User) session.getAttribute("customer");

        if (user == null) {
            user = (User) session.getAttribute("employee");
        }

        if (user == null) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            resp.getWriter().write(new ObjectMapper().writeValueAsString(user));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        String password;

        if (username == null || (password = req.getParameter("password")) == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Repository repo = (Repository) req.getServletContext().getAttribute("repo");
        HttpSession session = req.getSession();
        Customer customer = repo.login(username, password);

        if (customer != null) {
            if (customer.isEnabled()) {
                session.setAttribute("customer", customer);
            } else {
                session.invalidate();
                resp.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
            }
            return;
        }

        Employee employee = repo.loginEmployee(username, password);

        if (employee != null) {
            session.setAttribute("employee", employee);
            return;
        }

        session.invalidate();
        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}