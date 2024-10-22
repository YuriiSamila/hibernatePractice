package org.example.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "company_association")
public class CompanyAssociation implements Serializable {

    @EmbeddedId
    private CompanyAssociationId id;

    @ManyToOne
    @MapsId("workerId")
    private Worker worker;

    @ManyToOne
    @MapsId("companyId")
    private Company company;

    @ManyToOne
    @MapsId("departmentId")
    private Department department;

    public CompanyAssociation(Worker worker, Company company, Department department) {
        this.worker = worker;
        this.company = company;
        this.department = department;
        this.id = new CompanyAssociationId(worker.getId(), company.getId(), department.getId());
    }

    public CompanyAssociationId getId() {
        return id;
    }

    public void setId(CompanyAssociationId id) {
        this.id = id;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
