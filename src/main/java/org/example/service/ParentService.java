package org.example.service;

import org.example.entities.Parent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import static org.example.utils.Gender.MALE;

public class ParentService extends Service {

    public ParentService() {
        super();
    }

    public Parent saveParent() {
        Session session = getSession();
        Parent parent = new Parent("Gordon", MALE, 71);
        session.persist(parent);
        return parent;
    }

    public Parent updateParent() {
        Session session = getSession();
        Parent parent = new Parent("Fridge", MALE, 22);
        parent.setId(1);
        session.merge(parent);
        return parent;
    }

}