package org.example.entities;

import jakarta.persistence.*;
import org.example.utils.Gender;

import java.util.Objects;

import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.*;

@Entity
public class Child extends Person {

    //    @Id
//    @GeneratedValue(strategy = IDENTITY)
//    private int id;
//    @Column
//    private String name;
//    @Enumerated(STRING)
//    private Gender gender;
//    @Column
//    private Integer age;
    @OneToOne(cascade = REMOVE)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Parent parent;

    public Child() {
    }

    public Child(String name, Gender gender, Integer age, Parent parent) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.parent = parent;
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

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Child child = (Child) o;
        return id == child.id && Objects.equals(name, child.name) && gender == child.gender && Objects.equals(age, child.age) && Objects.equals(parent, child.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender, age, parent);
    }

    @Override
    public String toString() {
        return "Child{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                '}';
    }
}
