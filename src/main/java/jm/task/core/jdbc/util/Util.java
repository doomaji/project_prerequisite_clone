package jm.task.core.jdbc.util;

import java.sql.*;
import java.util.Properties;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/example_schema";
    private static final String USER = "root";
    private static final String PASS = "Password123";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Connected to database successfully");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return connection;
    }

    public static class HibernateUtil {

        private static SessionFactory sessionFactory;

        private static SessionFactory buildSessionFactory() {
            try {
                Configuration configuration = new Configuration();

                //Create Properties, can be read from property files too
                Properties props = new Properties();
                props.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                props.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/example_schema");
                props.put("hibernate.connection.username", "root");
                props.put("hibernate.connection.password", "Password123");
                props.put("hibernate.current_session_context_class", "thread");

                configuration.setProperties(props);

                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
                System.out.println("Hibernate Java Config serviceRegistry created");

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

                return sessionFactory;
            }
            catch (Throwable ex) {
                System.err.println("Initial SessionFactory creation failed." + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }

        public static SessionFactory getSessionFactory() {
            if (sessionFactory == null) sessionFactory = buildSessionFactory();
            return sessionFactory;
        }
    }
}
