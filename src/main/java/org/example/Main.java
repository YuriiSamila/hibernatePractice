package org.example;

import org.example.entities.Parent;
import org.example.service.ParentService;
import org.example.service.Service;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Map;


public class Main {
    public static void main(String[] args) {
        Service service = new Service();
//        System.out.println(service.getParent());
        try {

            service.updateUsingFlush();
            service.commitTransaction();
        } catch (Exception e) {
            if (service.getTransaction() != null) {
                service.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (service.getSession() != null) {
                service.getSession().close();
            }
        }
    }
}