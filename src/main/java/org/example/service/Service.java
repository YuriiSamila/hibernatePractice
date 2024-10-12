package org.example.service;

import org.example.entities.Child;
import org.example.entities.Parent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import static org.example.utils.Gender.FEMALE;
import static org.example.utils.Gender.MALE;

public class Service {

    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    public Service() {
            sessionFactory = new Configuration()
                    .addAnnotatedClass(Parent.class)
                    .addAnnotatedClass(Child.class)
                    .buildSessionFactory();
            session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Session getSession() {
        return session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void commitTransaction() {
        transaction.commit();
    }

    public Parent saveParent() {
        Session session = getSession();
        Parent parent = new Parent("Gordon", MALE, 71);
        session.persist(parent);
        return parent;
    }

    public Parent updateParent() {
        Session session = getSession();
        Parent parent = new Parent("GOGOG", MALE, 50);
        parent.setId(3);
        session.merge(parent);
        return parent;
    }

    public Parent getParent() {
        Session session = getSession();
        return session.get(Parent.class, 4);
    }

    public void removeParent() {
        Session session = getSession();
        Parent parent = session.get(Parent.class, 3);
        session.remove(parent);
    }

    public void updateChild(Parent parent) {
        Session session = getSession();
        Child child = new Child("Samantha", FEMALE, 20, parent);
        child.setId(1);
        session.merge(child);
    }

    public void updateUsingFlush() {
        Session session = getSession();
        Parent parent = new Parent("Flushed", MALE, 44);
        session.persist(parent);
        Child child = session.get(Child.class, 2);
        child.setParent(parent);
        session.flush();
    }

}
