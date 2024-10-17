package org.example.service;

import org.example.entities.Child;
import org.example.entities.Parent;
import org.hibernate.Session;

import static org.example.utils.Gender.FEMALE;

public class ChildService extends ServiceMySql {

    public ChildService() {
        super();
    }

    public void updateChild(Parent parent) {
        Session session = getSession();
        Child child = new Child("Samantha", FEMALE, 20, parent);
        child.setId(1);
        session.merge(parent);
    }

}
