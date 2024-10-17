package org.example;

import org.example.service.Service;


public class Main {
    public static void main(String[] args) {
        Service service = new Service();
//        System.out.println(service.getParent());
        try {
            service.saveParent();
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