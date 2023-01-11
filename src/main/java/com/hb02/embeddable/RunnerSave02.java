package com.hb02.embeddable;

import com.hb01.annotation.Student01;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RunnerSave02 {
    public static void main(String[] args) {
        Student02 student02 = new Student02();
        student02.setId(1001);
        student02.setName("Tom hanks");
        student02.setGrad(100);

        Address address1 = new Address();
        address1.setCity("Aplle");
        address1.setCountry("Japan");
        address1.setZipCode("3243");
        address1.setStreet("Mango Street");
        student02.setAddress(address1);

        Address address2 = new Address();
        address2.setCity("Orange");
        address2.setCountry("Kz");
        address2.setZipCode("5345");
        address2.setStreet("Hello Street");

        Student02 student01 = new Student02();
        student01.setId(1002);
        student01.setName("Jim Hhan");
        student01.setGrad(90);

        student01.setAddress(address2);
        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student02.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        session.save(student01);
        session.save(student02);

        tx.commit();
        session.close();
        sf.close();


    }
}
