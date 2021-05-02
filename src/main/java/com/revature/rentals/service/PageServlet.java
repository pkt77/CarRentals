package com.revature.rentals.service;

import org.apache.commons.io.FileUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

@WebServlet({"", "/welcome", "/login", "/services", "/register", "/contact"})
public class PageServlet extends HttpServlet {
    private String index;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        try {
            index = FileUtils.readFileToString(new File(config.getServletContext().getRealPath("/index.html")), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html; charset=UTF-8");

        if (req.getHeader("loaded") == null) {
            resp.getWriter().write(index);
        } else {
            req.getRequestDispatcher("/views" + req.getRequestURI() + ".html").forward(req, resp);
        }
    }
}