package com.revature.rentals;

import com.revature.rentals.repo.PostgresRepository;
import com.revature.rentals.repo.Repository;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebListener
public class Listener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {
    private Repository repo;

    public Listener() {}

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Map<String, String> settings = new HashMap<>();
            BufferedReader reader = new BufferedReader(new FileReader(sce.getServletContext().getRealPath("/WEB-INF/db.properties")));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] setting = line.split("=");

                settings.put(setting[0], setting[1]);
            }

            repo = new PostgresRepository(settings.get("address"), settings.get("username"), settings.get("password"));

            sce.getServletContext().setAttribute("repo", repo);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            repo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
