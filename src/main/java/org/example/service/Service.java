package org.example.service;

import org.example.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.graph.RootGraph;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Thread.sleep;

public class Service {

    private final SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    public Service() {
        sessionFactory = new Configuration()
                .addAnnotatedClass(CustomEntity.class)
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(Building.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
//        session = sessionFactory.getCurrentSession();
//        transaction = session.beginTransaction();
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

    public void statelessSessionSelectUpdate() {
        StatelessSession statelessSession = sessionFactory.openStatelessSession();
        statelessSession.getTransaction().begin();
        Person user = new Person();
        user.setName("Andrew");
        user.setAge(77);
        statelessSession.insert(user);
        Address address = new Address();
        address.setNumber(485);
        address.setStreet("SomeStreet");
        address.setPerson(user);
        statelessSession.insert(address);
        user.setName("ChangedName");
        statelessSession.update(user); // doesn't work without update() unlike with the common session
        statelessSession.getTransaction().commit();
        sessionFactory.close();
    }

    public Address getWithPersonEntityGraph() {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.getTransaction().begin();
        RootGraph<?> personGraph = currentSession.getEntityGraph("PERSON_GRAPH");
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.fetchgraph", personGraph);
        Address address = currentSession.find(Address.class, 1L, properties);
        currentSession.getTransaction().commit();
        sessionFactory.close();
        return address;
    }

    public void checkSupportBatches() {
        Connection connection = null;
        try {
            connection = sessionFactory.getSessionFactoryOptions().getServiceRegistry().getService(ConnectionProvider.class)
                    .getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            System.out.println(connection.getMetaData().supportsBatchUpdates());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void batchInsert() {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.getTransaction().begin();
        List<Course> courses = new ArrayList<>();
        for (int i = 1; i <=10; i++) {
            Course course = new Course();
            course.setName("Course_" + i);
            currentSession.persist(course);
            courses.add(course);
        }
        for (int i = 1; i <=10; i++) {
            Student student = new Student();
            student.setName("Student_" + i);
            student.setCourses(courses);
            currentSession.persist(student);
            if (i % 5 == 0) {
                currentSession.flush();
                currentSession.clear();
            }
        }
        currentSession.getTransaction().commit();
        sessionFactory.close();
    }

}
