package org.example;

import org.example.entities.Address;
import org.example.service.Service;


public class Main {
    public static void main(String[] args) {
        Service service = new Service();
        try {
            service.batchInsert();
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