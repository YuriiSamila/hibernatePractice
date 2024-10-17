package org.example.service;

import org.example.entities.CustomEntity;
import org.example.entities.CustomType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import static java.lang.Thread.sleep;

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

    public void firstLevelCacheExample() throws InterruptedException {
        CustomEntity customEntity1 = session.getReference(CustomEntity.class, 2);
        System.out.println("Entity1: " + customEntity1);
        sleep(15000);

        CustomEntity customEntity2 = session.getReference(CustomEntity.class, 2);
        System.out.println("Entity2: " + customEntity2);
        // if any column for CustomEntity has been changed during Thread.sleep() (via DB), customEntity2 will be the same
        // as customEntity1, because of caching

        Session newSession = sessionFactory.openSession();
        CustomEntity newSessionEntity = newSession.getReference(CustomEntity.class, 2);
        System.out.println("NewSessionEntity: " + newSessionEntity);
        // but here, newSessionEntity will have updated values because newSession will create a new SELECT query
        session.evict(customEntity1); // removes customEntity from the cache
        session.clear(); // completely clear the session
    }

}
