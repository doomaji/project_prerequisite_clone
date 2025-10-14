package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public final class Util {
    private static final String URL  = "jdbc:mysql://localhost:3306/example_schema";
    private static final String USER = "root";
    private static final String PASS = "Password123";

    private static final ServiceRegistry SERVICE_REGISTRY;
    private static final SessionFactory SESSION_FACTORY;

    static {
        try {
            Configuration configuration = new Configuration();

            configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            configuration.setProperty("hibernate.connection.url", URL);
            configuration.setProperty("hibernate.connection.username", USER);
            configuration.setProperty("hibernate.connection.password", PASS);
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            configuration.addAnnotatedClass(jm.task.core.jdbc.model.User.class);

            SERVICE_REGISTRY = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            System.out.println("Hibernate Java Config serviceRegistry created");

            SESSION_FACTORY = configuration.buildSessionFactory(SERVICE_REGISTRY);
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed. " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private Util() {}

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }
}
