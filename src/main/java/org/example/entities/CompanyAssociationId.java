package org.example.entities;

import jakarta.persistence.Embeddable;

@Embeddable
public class CompanyAssociationId {

    private Long workerId;
    private Long companyId;
    private Long departmentId;

    public CompanyAssociationId(Long workerId, Long companyId, Long departmentId) {
        this.workerId = workerId;
        this.companyId = companyId;
        this.departmentId = departmentId;
    }

    public CompanyAssociationId() {
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
