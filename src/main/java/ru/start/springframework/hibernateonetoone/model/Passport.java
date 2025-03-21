package ru.start.springframework.hibernateonetoone.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "Passport")
// Если в качестве первичного ключа мы используем не число - Serializable!
public class Passport {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "passport_number")
    private int passportNumber;

    @OneToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    public Passport() {
    }

    public Passport(int passportNumber) {
        this.passportNumber = passportNumber;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Person getPerson() {
        return person;
    }
    public void setPerson(Person person) {
        this.person = person;
    }

    public int getPassportNumber() {
        return passportNumber;
    }
    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }
    @Override
    public String toString() {
        return "Passport{" +
                "person=" + id +
                ", passportNumber=" + passportNumber +
                '}';
    }
}
