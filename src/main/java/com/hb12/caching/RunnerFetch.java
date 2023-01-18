package com.hb12.caching;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RunnerFetch {
    public static void main(String[] args) {
        Configuration con = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Student12.class);
        //addAnnotatedClass(Book09.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();


        // 1st Level cache -- istep tur ma koreik

        //1. run this
        System.out.println("first get method for student whose id is: 1");
        Student12 student1 = session.get(Student12.class, 1L);


        //3. run this
        //session.clear(); // biz 1st level cache jaba almaimiz paidasi bar, birak memory delete yapabilirz

        //2. run this
        // run this and see the console
        System.out.println("Second get method for student whose id is: 1");
        Student12 student2 = session.get(Student12.class, 1L); // second select did not work
        // because of 1st level cache and it is open in the same session


        tx.commit();
        session.close();

        //open another session

        Session session2 = sf.openSession();
        Transaction tx2 = session2.beginTransaction();

        System.out.println("Third get method in new session for student whose id is: 1");
        Student12 student3 = session2.get(Student12.class, 1L); // second select did not work

        tx2.commit();
        session2.close();
        sf.close();
    }
}
