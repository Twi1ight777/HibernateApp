package ru.start.springframework.hibernateonetomany.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.start.springframework.hibernateonetomany.model.model.Item;
import ru.start.springframework.hibernateonetomany.model.model.Person;

public class App
{
    public static void main( String[] args ){
// Создаем конфигурацию с таблицей Person
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);

        // Создаем ServiceRegistry для настройки Hibernate
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());

        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();
            /// Каскадирование в Hibernate. В базу данных добавляется 1 человек и 3 товара связанные с ним
            Person person = new Person("Test cascading",30);
            // У айтема есть человек person
            person.addItem(new Item("Item 1"));
            person.addItem(new Item("Item 2"));
            person.addItem(new Item("Item 3"));

            // В чем разница между Persist и Save?
            session.persist(person);
            //session.persist(item);
            ///Разница между Persist и Save. Save - из самой библиотеки Hibernate(нет в JPA);
            /// Возвращает значение первичного ключа для добавленной сущности;
            /// Гарантирует, что значение первичного ключа будет определено сразу же после вызова.
//            /// Изменяем владельца товара
//            Person person = session.get(Person.class,4);
//            Item item = session.get(Item.class,1);
//            // Удаляем у старого владельца товар
//            item.getOwner().getItems().remove(item);
//            // Добавляем нового владельца на товар в двух таблицах
//            item.setOwner(person);
//            person.getItems().add(item);
///            // Удаление человека
//            Person person = session.get(Person.class,2);
//            // SQL
//            session.remove(person);
//            // Было правильное состояние Hibernate кэша. Каждому айтему назначается владелец null
//            person.getItems().forEach(i -> i.setOwner(null));
///            //Удаление предмета
//            Person person = session.get(Person.class,3);
//            List<Item> items = person.getItems();
//            // SQL
//            for (Item item: items) {
//                session.remove(item);
//            }
//            // Не порождает SQL, но необходимо для того, чтобы в кэше всё было верно
//            person.getItems().clear();

///            // Создаем и сохраняем человека и связанный с ним товар
//            Person person = new Person("Test Person",30);
//            Item newItem = new Item("Test Item",person);
//
//            person.setItems(new ArrayList<>(Collections.singletonList(newItem)));
//            // Сохраняем человека и связанных с ним товар
//            // Не настроено каскадирование! Необходимо сохранять и то и то
//            session.save(person);
//            session.save(newItem);
//            Person person = session.get(Person.class,2);
///            // Связь на стороне предмета
//            Item newItem = new Item("New Item", person);
//            // Связь на стороне человека нужна, если на стороне Hibernate и таблицы в базе данных должны совпадать значения
//            // Не влияет на базу данных, влияет на кэш Hibernate
//            person.getItems().add(newItem);
//            session.save(newItem);
///            // Находим человека владеющего товаром
//            Item item = session.get(Item.class, 3);
//            System.out.println("Item loaded: " + item);
//            Person person = item.getOwner();
//            System.out.println("Person loaded: " + person);
///            // Находим все товары у человека
//            Person person = session.get(Person.class,3);
//            System.out.println("Person loaded: " + person);
//
//            List<Item> items = person.getItems();
//            System.out.println("Items loaded: " + items);

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }
}
