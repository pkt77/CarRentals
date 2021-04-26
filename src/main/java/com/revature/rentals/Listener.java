package com.revature.rentals;

import com.revature.rentals.repo.HibernateRepository;
import com.revature.rentals.repo.Repository;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.mailer.MailerBuilder;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

                if (setting.length > 1) {
                    settings.put(setting[0], setting[1]);
                }
            }

            repo = new HibernateRepository(settings.get("address"), settings.get("username"), settings.get("password"));
            //new PostgresRepository(settings.get("address"), settings.get("username"), settings.get("password"));

            sce.getServletContext().setAttribute("repo", repo);

            Mailer mailer = MailerBuilder.withTransportStrategy(TransportStrategy.SMTP_TLS)
                    .withSMTPServer(settings.get("smtp_host"), Integer.parseInt(settings.get("smtp_port")), settings.get("email"), settings.get("email_password")).buildMailer();

            mailer.testConnection();
            sce.getServletContext().setAttribute("mailer", mailer);
        } catch (IOException e) {
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
