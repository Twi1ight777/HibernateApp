package ru.start.springframework.hibernatemanytomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.start.springframework.hibernatemanytomany.model.Actor;
import ru.start.springframework.hibernatemanytomany.model.Movie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App
{
    public static void main( String[] args ){
        // Создаем конфигурацию с таблицами
        Configuration configuration = new Configuration().addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Movie.class);

        // Создаем ServiceRegistry для настройки Hibernate
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());

        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());

        // Try with resources
        try (sessionFactory){
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            /// Добавляем в таблицу актеров и фильм
//            Movie movie = new Movie("Pulp fiction", 1994);
//            Actor actor1 = new Actor("Harlvey Keitel", 81);
//            Actor actor2 = new Actor("Samuel L. Jackson", 77);
//            //Arrays.asList(actor1, actor2) - изменяемый список, но не расширяемый
//            movie.setActors(new ArrayList<>(List.of(actor1, actor2))); // неизменяемый список
//            actor1.setMovies(new ArrayList<>(Collections.singletonList(movie)));
//            actor2.setMovies(new ArrayList<>(Collections.singletonList(movie)));
//
//            session.persist(movie);
//            session.persist(actor1);
//            session.persist(actor2);
            /// Выводим актеров по фильму
//            Movie movie = session.get(Movie.class, 1);
//            System.out.println("Movie Actors: " + movie.getActors());
//
//            session.getTransaction().commit();
            ///  Обновляем данные в бд
//            Movie movie = new Movie("Reservoir Dogs", 1992);
//            Actor actor = session.get(Actor.class, 1);
//
//            movie.setActors(new ArrayList<>(Collections.singletonList(actor)));
//            actor.getMovies().add(movie);
//
//            session.persist(movie);

            /// Удаляем данные из бд
            Actor actor = session.get(Actor.class,2);
            System.out.println(actor.getMovies());

            Movie movieToRemove = actor.getMovies().get(0); // Индекс 0 = первый в списке

            actor.getMovies().remove(0);
            movieToRemove.getActors().remove(actor); // хешкод и иквлс => объекты можно сравнивать между собой на равенство и метод работает

            session.getTransaction().commit();

        }
    }
}
