package com.hb06.uni_onetomany;

import com.hb05.manytoone.Student05;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class RunnerSave06 {
    public static void main(String[] args) {

        Student06 student1 = new Student06();
        student1.setId(1001);
        student1.setName("AAA");
        student1.setGrade(98);

        Student06 student2 = new Student06();
        student2.setId(1002);
        student2.setName("AAA");
        student2.setGrade(98);

        Student06 student3 = new Student06();
        student3.setId(1003);
        student3.setName("AAA");
        student3.setGrade(98);

        Boook06 book1 = new Boook06();
        book1.setId(101);
        book1.setName("Java Book");

        Boook06 book2 = new Boook06();
        book2.setId(102);
        book2.setName("Hibernate Book");

        Boook06 book3 = new Boook06();
        book3.setId(103);
        book3.setName("JDBC Book");

        student1.getBookList().add(book1);
        student1.getBookList().add(book2);
        student2.getBookList().add(book3);

        Configuration con = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Student06.class).
                addAnnotatedClass(Boook06.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();


        session.save(student1);
        session.save(student2);
        session.save(student3);

        session.save(book1);
        session.save(book2);
        session.save(book3);


        tx.commit();
        session.close();
        sf.close();
    }
}
