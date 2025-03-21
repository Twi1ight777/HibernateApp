package ru.start.springframework.hibernateonetomany.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany (mappedBy = "owner", cascade = {CascadeType.PERSIST, CascadeType.DETACH}) // Делаем ссылку на owner
    private List<Item> items;

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

    public List<Item> getItems() {
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
    public void addItem(Item item) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        this.items.add(item);
        item.setOwner(this);
    }
}
