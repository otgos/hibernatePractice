package com.hb08.manytomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RunnerFetch08 {
    public static void main(String[] args) {
        Configuration con = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Student08.class).
                addAnnotatedClass(Book08.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();


        //get a student by Id with get()

//        Student08 std1 = session.get(Student08.class, 1001);
//        System.out.println(std1);


        //get a book by Id with get()
        Book08 book1 = session.get(Book08.class, 101);
        System.out.println(book1);
        tx.commit();
        session.close();
        sf.close();
    }
}
