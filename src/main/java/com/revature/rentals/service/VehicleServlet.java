package com.revature.rentals.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.rentals.repo.Repository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/vehicles/*")
public class VehicleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Repository repo = (Repository) req.getServletContext().getAttribute("repo");

        resp.getWriter().write(new ObjectMapper().writeValueAsString(repo.getVehicles()));
    }
}