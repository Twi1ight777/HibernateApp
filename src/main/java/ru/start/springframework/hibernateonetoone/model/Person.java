package ru.start.springframework.hibernateonetoone.model;

import jakarta.persistence.*;

@Entity // Сущность
@Table(name = "Person") // Название таблицы

public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // В таблице Person есть колонка id, которая генерируется автоматически IDENTITY
    // стратегия SEQUENCE нужна для автоматического метода next, для другой базы данных
    // стратегия TABLE если необходима отдельная таблица для генерации id
    private int id; // Генерируются автоматически с помощью PostgreSQL, Hibernate этим не занимается
    @Column(name = "name") // Название столбца имени
    private String name;
    @Column(name = "age") // Название столбца возраста
    private int age;

    @OneToOne(mappedBy = "person", cascade = {CascadeType.PERSIST, CascadeType.DETACH}) // Делаем ссылку на person
    private Passport passport; // Связь один к одному, внешний ключ совпадает с id в таблице Passport


    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public Passport getPassport() {
        return passport;
    }
    // Всегда будет выполняться двусторонняя связь
    public void setPassport(Passport passport) {
        this.passport = passport;
        passport.setPerson(this);
    }

    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
