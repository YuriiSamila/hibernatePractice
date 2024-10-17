package org.example.entities;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "custom_table")
public class CustomEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @Column(length = 30)
    private String name;

    @Embedded
    @Column(name = "my_type")
    private CustomType customType;

    public CustomEntity() {
    }

    public CustomEntity(String name, CustomType customType) {
        this.name = name;
        this.customType = customType;
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

    public CustomType getCustomType() {
        return customType;
    }

    public void setCustomType(CustomType customType) {
        this.customType = customType;
    }

    @Override
    public String toString() {
        return "CustomEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", customType=" + customType +
                '}';
    }
}
