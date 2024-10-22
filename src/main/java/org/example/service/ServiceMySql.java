package org.example.service;

import jakarta.persistence.Tuple;
import org.example.entities.Child;
import org.example.entities.Parent;
import org.example.entities.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;


import java.util.List;

import static org.example.utils.Gender.FEMALE;
import static org.example.utils.Gender.MALE;

public class ServiceMySql {

    private final SessionFactory sessionFactory;
    private final Session session;
    private final Transaction transaction;

    public ServiceMySql() {
        sessionFactory = new Configuration()
                .addAnnotatedClass(Parent.class)
                .addAnnotatedClass(Child.class)
                .addAnnotatedClass(Person.class)
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

    public Parent getParent(int id) {
        Session session = getSession();
        return session.get(Parent.class, id);
    }

    public Person getPerson(int id) {
        Session session = getSession();
        Query<Person> query = session.createQuery("FROM Person WHERE id = :id", Person.class);
        return query.setParameter("id", id)
                .getSingleResult();
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

//    public List<Parent> getParentsCriteriaAPI() {
//        Session session = getSession();
//        HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//        JpaCriteriaQuery<Parent> criteriaQuery = criteriaBuilder.createQuery(Parent.class);
//        JpaRoot<Parent> root = criteriaQuery.from(Parent.class);
//        JpaCriteriaQuery<Parent> selected = criteriaQuery.select(root);
//        Query<Parent> query = session.createQuery(criteriaQuery);
//        return query.getResultList();
//    }
//
//    public List<Parent> getParentsCriteriaAPIById(int id) {
//        Session session = getSession();
//        HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//        JpaCriteriaQuery<Parent> criteriaQuery = criteriaBuilder.createQuery(Parent.class);
//        JpaRoot<Parent> root = criteriaQuery.from(Parent.class);
//        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));
//        Query<Parent> query = session.createQuery(criteriaQuery);
//        return query.getResultList();
//    }
//
//    public List<Parent> getParentsCriteriaAPITwoPredicates(int id, String column) {
//        Session session = getSession();
//        HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//        JpaCriteriaQuery<Parent> criteriaQuery = criteriaBuilder.createQuery(Parent.class);
//        JpaRoot<Parent> root = criteriaQuery.from(Parent.class);
//        JpaPredicate idPredicate = criteriaBuilder.equal(root.get("id"), id);
//        JpaPredicate namePredicate = criteriaBuilder.isNotNull(root.get(column));
//        criteriaQuery.select(root).where(criteriaBuilder.or(idPredicate, namePredicate)).orderBy(criteriaBuilder.asc(root.get("age")));
//        Query<Parent> query = session.createQuery(criteriaQuery);
//        return query.getResultList();
//    }

    public void getParentsNativeQueryTuple() {
        Session session = getSession();
        NativeQuery<Tuple> nativeQuery = session.createNativeQuery("SELECT name, age, id FROM parents WHERE gender = :genderok", Tuple.class);
        nativeQuery.setParameter("genderok", "male");
        List<Tuple> resultList = nativeQuery.getResultList();
        for (Tuple tuple : resultList) {
            System.out.println(tuple.get("name") + " - " + tuple.get("age") + " - " + tuple.get("id"));
        }
    }

    public void getParentsJPQLTuple() {
        Session session = getSession();
        Query<Tuple> query = session.createQuery("SELECT name as name, age as age FROM Parent WHERE gender = :gender", Tuple.class);
        query.setParameter("gender", MALE);
        List<Tuple> resultList = query.getResultList();
        for (Tuple tuple : resultList) {
            System.out.println(tuple.get("name") + " - " + tuple.get("age"));
        }
    }

}
