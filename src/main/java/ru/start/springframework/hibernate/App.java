package ru.start.springframework.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.start.springframework.hibernate.model.Person;

public class App
{
    public static void main( String[] args ){
// Создаем конфигурацию с таблицей Person
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

        // Создаем ServiceRegistry для настройки Hibernate
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());

        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();
            Person person1 = new Person("Test1", 20);
            Person person2 = new Person("Test2", 21);
            Person person3 = new Person("Test3",22);

            session.save(person1);
            session.save(person2);
            session.save(person3);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
