package ru.start.springframework.hibernateidentity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.start.springframework.hibernateidentity.model.Person;

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

            // Выполняем запросы к базе
            session.createQuery("delete from Person where age < 30").executeUpdate();

//            for (Person person : people) {
//                System.out.println(person);
//            }
//            // Сохраняем человека в таблицу
//            Person person = new Person("Some name", 60);
//            session.save(person);

//            Person person = session.get(Person.class, 2);
//            
//            // Переписываем имя по id
//            person.setName("New name");
//            // Удаление человека с id 2
//            session.delete(person);

            session.getTransaction().commit();

//            System.out.println("Person saved successfully " + person.getId());

        } finally {
            sessionFactory.close();
        }
    }
}
