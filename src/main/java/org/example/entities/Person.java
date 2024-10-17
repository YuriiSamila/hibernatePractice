package org.example.entities;

import jakarta.persistence.*;
import org.example.utils.Gender;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.TABLE;
import static jakarta.persistence.InheritanceType.TABLE_PER_CLASS;

@Entity
@Inheritance(strategy = TABLE_PER_CLASS)
public class Person {

    @Id
    @GeneratedValue(strategy = TABLE)
    protected int id;
    @Column
    protected String name;
    @Enumerated(STRING)
    protected Gender gender;
    @Column
    protected Integer age;

    public Person() {
    }

    public Person(int id, String name, Gender gender, Integer age) {
        this.id = id;
        this.name = name;
        this.gender = gender;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setChild(Child child) {

    }

    public void setParent(Parent parent) {

    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                '}';
    }
}
