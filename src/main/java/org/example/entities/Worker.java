package org.example.entities;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "worker")
    Set<CompanyAssociation> associations = new HashSet<>();

    @OneToMany
    @JoinTable(name = "company_association", joinColumns = @JoinColumn(name = "worker_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "department_id", referencedColumnName = "id"))
    @MapKeyJoinColumn(name = "company_id")
    Map<Company, Department> companyDepartmentMap = new HashMap<>();

    public Worker(String name) {
        this.name = name;
    }

    public Worker() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CompanyAssociation> getAssociations() {
        return associations;
    }

    public void setAssociations(Set<CompanyAssociation> associations) {
        this.associations = associations;
    }

    public Map<Company, Department> getCompanyDepartmentMap() {
        return companyDepartmentMap;
    }

    public void setCompanyDepartmentMap(Map<Company, Department> companyDepartmentMap) {
        this.companyDepartmentMap = companyDepartmentMap;
    }
}
