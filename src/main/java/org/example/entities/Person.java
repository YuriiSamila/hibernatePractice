package org.example.entities;

import jakarta.persistence.*;
import org.example.utils.Gender;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@MappedSuperclass
public class Person {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    protected int id;
    @Column
    protected String name;
    @Enumerated(STRING)
    protected Gender gender;
    @Column
    protected Integer age;

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
}
