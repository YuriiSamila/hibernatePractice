package org.example.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import org.example.utils.Gender;

import java.util.Objects;

@Entity
@DiscriminatorValue("parent")
public class Parent extends Person {

    //    @Id
////    @GeneratedValue(generator = IdGenerator.NAME)
////    @GenericGenerator(name = IdGenerator.NAME, type = IdGenerator.class) - alternative(deprecated)
//    @GeneratedValue(strategy = IDENTITY)
//    private int id;
//    @Column
//    private String name;
//    //@Enumerated(STRING) - the same as a @Convert
//    @Convert(converter = GenderColumnConvertor.class)
//    private Gender gender;
//    @Column
//    private Integer age;
    @OneToOne(mappedBy = "parent")
    private Child child;

    public Parent() {
    }

    public Parent(String name, Gender gender, Integer age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public Parent(String name, Gender gender, Integer age, Child child) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.child = child;
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

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
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
