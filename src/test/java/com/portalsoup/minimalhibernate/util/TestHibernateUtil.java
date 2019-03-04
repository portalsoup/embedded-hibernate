package com.portalsoup.minimalhibernate.util;

import com.portalsoup.minimalhibernate.entity.DiscoverableEntity;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.reflections.Reflections;

import java.util.Properties;

public class TestHibernateUtil extends HibernateUtil {

    protected static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "org.h2.Driver");
                settings.put(Environment.URL, "jdbc:h2:./database/test");
                settings.put(Environment.USER, "ci");
                settings.put(Environment.PASS, "");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");

                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "update");

                configuration.setProperties(settings);

                registerEntities(configuration);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static void resetDatabase() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    private static void registerEntities(Configuration configuration) {
        Reflections reflections = new Reflections("com.portalsoup.minimalhibernate.entity");
        reflections.getSubTypesOf(DiscoverableEntity.class).stream()
                .peek(System.out::println)
                .forEach(configuration::addAnnotatedClass);
    }
}
