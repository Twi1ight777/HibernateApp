package ru.start.springframework.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Person") // Название таблицы
public class Person {
    @Id
    @Column(name = "id")
    private int id; // ID персоны
    @Column(name = "name") // Название столбца имени
    private String name;
    @Column(name = "age") // Название столбца возраста
    private int age;

    public Person() {
    }
    public Person(int id, String name, int age) {
        this.id = id;
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
}
