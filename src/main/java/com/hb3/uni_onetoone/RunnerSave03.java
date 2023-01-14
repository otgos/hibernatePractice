package com.hb3.uni_onetoone;

import com.hb02.embeddable.Student02;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RunnerSave03 {
    public static void main(String[] args) {
        Student03 student01 = new Student03();
        student01.setName("AAA");
        student01.setId(1001);
        student01.setGrade(90);

        Student03 student02 = new Student03();
        student02.setName("BBB");
        student02.setId(1002);
        student02.setGrade(80);

        Student03 student03 = new Student03();
        student03.setName("CCC");
        student03.setId(1003);
        student03.setGrade(90);

        Student03 student04 = new Student03();
        student04.setName("DDD");
        student04.setId(1004);
        student04.setGrade(60);

        DIary dIary1 = new DIary();
        dIary1.setId(101);
        dIary1.setName("AAA's diary");
        dIary1.setStudent(student01);

        DIary dIary2 = new DIary();
        dIary2.setId(102);
        dIary2.setName("BBB's diary");
        dIary2.setStudent(student02);

        DIary dIary3 = new DIary();
        dIary3.setId(103);
        dIary3.setName("CCC's diary");
        dIary3.setStudent(student02);


        Configuration con = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Student03.class).
                addAnnotatedClass(DIary.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

//        session.save(student01);
//        session.save(student02);
//        session.save(student03);

//        session.save(dIary1);
//        session.save(dIary2);
//        session.save(dIary3);
        session.save(student04);

        tx.commit();
        sf.close();
        session.close();


    }
}
