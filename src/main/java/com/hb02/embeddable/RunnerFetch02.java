package com.hb02.embeddable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RunnerFetch02 {
    public static void main(String[] args) {
        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student02.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Student02 student02 = session.get(Student02.class, 1001);
        System.out.println(student02);
        System.out.println(student02.getAddress()); //we can work with address independently, we are embedding object inside an object


        tx.commit();
        sf.close();
        session.close();
    }
}
