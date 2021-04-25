package com.revature.rentals.service;

import com.revature.rentals.data.Customer;
import com.revature.rentals.repo.Repository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/api/login")
public class LoginServlet extends HttpServlet {

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

        if (customer == null) {
            session.invalidate();

            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        session.setAttribute("customer", customer);
    }
}