package org.example.service;

import org.example.entities.Child;
import org.example.entities.Parent;
import org.hibernate.Session;

import static org.example.utils.Gender.FEMALE;
import static org.example.utils.Gender.MALE;

public class ChildService extends Service {

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
