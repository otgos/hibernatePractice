package com.hb3.uni_onetoone;

import com.hb02.embeddable.Student02;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RunnerFetch03 {
    public static void main(String[] args) {
        Configuration con = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Student03.class).
                addAnnotatedClass(DIary.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        //stdent fetch (get) one student by id
        Student03 student01 = session.get(Student03.class, 1001);


        //fetch diary
         DIary dIary = session.get(DIary.class, 101);
        System.out.println(student01);
        System.out.println("################");
        System.out.println(dIary);
        System.out.println("################");
        System.out.println(dIary.getStudent());
        System.out.println("################");


        tx.commit();
        sf.close();
        session.close();
    }
}
