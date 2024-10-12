package org.example.entities;

import jakarta.persistence.*;
import org.example.utils.Gender;

import java.util.Objects;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "parents")
public class Parent {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    @Column
    private String name;
    @Enumerated(STRING)
    private Gender gender;
    @Column
    private Integer age;
    @OneToOne(mappedBy = "parent")
    private Child child;

    public Parent() {
    }

    public Parent(String name, Gender gender, Integer age) {
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parent parent = (Parent) o;
        return id == parent.id && Objects.equals(name, parent.name) && gender == parent.gender && Objects.equals(age, parent.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender, age);
    }

    @Override
    public String toString() {
        return "Parent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", child=" + child +
                ", gender=" + gender +
                ", age=" + age +
                '}';
    }
}
