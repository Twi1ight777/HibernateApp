package ru.start.springframework.hibernateonetoone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.start.springframework.hibernateonetoone.model.Passport;
import ru.start.springframework.hibernateonetoone.model.Person;

public class App
{
    public static void main( String[] args ){
// Создаем конфигурацию с таблицей Person
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class)
                .addAnnotatedClass(Passport.class);

        // Создаем ServiceRegistry для настройки Hibernate
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());

        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Person person = session.get(Person.class, 2);
            System.out.println(person.getPassport().getPassportNumber());
            // Сделаем наоборот
            Passport passport = session.get(Passport.class, 2);
            System.out.println(passport.getPerson().getName());
            // Изменим номер паспорта
            person.getPassport().setPassportNumber(777777);
            // Удалим человека, удалится ли паспорт? Удалится, тк. на уровне бд настроено каскадирование
            session.remove(person);

            session.getTransaction().commit();

//            Person person = new Person ("Test Person", 30);
//            /// Связываем паспорт с человеком. Owning side - passport (@JoinColumn)
//            Passport passport = new Passport(1234567890);
//            // В методе заранее создали двустороннюю связь из-за чего не надо писать Passport(person, 1234567890);
//            person.setPassport(passport);
//            // Каскадированное сохранение
//            session.persist(person);
//            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }
}
