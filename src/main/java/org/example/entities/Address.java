package org.example.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table
@NamedEntityGraphs({
        @NamedEntityGraph(name = "PERSON_GRAPH", attributeNodes = @NamedAttributeNode(value = "person")),
        @NamedEntityGraph(name = "PERSON_AND_BUILDING_GRAPH", attributeNodes = {@NamedAttributeNode(value = "person"),
        @NamedAttributeNode(value = "buildings")})
})
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String street;

    @Column(nullable = false)
    private int number;

    @JoinColumn(name = "person_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Person person;

    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Building> buildings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}
