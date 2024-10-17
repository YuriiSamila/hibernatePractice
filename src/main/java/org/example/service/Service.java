package org.example.service;

import org.example.entities.CustomEntity;
import org.example.entities.CustomType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Service {

    private final SessionFactory sessionFactory;
    private final Session session;
    private final Transaction transaction;

    public Service() {
        sessionFactory = new Configuration()
                .addAnnotatedClass(CustomEntity.class)
                .buildSessionFactory();
        session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
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

    public void saveCustomEntity() {
        Session session = getSession();
        CustomType customType = new CustomType(5, "Yeap");
        CustomEntity customEntity = new CustomEntity("Johnny", customType);
        session.persist(customEntity);
    }

    public CustomEntity getCustomEntity(int id) {
        Session session = getSession();
        return session.get(CustomEntity.class, id);
    }

}
