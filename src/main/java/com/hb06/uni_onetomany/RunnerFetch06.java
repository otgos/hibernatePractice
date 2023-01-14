package com.hb06.uni_onetomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.List;

public class RunnerFetch06 {
    public static void main(String[] args) {
        Configuration con = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Student06.class).
                addAnnotatedClass(Boook06.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        System.out.println("********* using get() method **********");
        Student06 student1 = session.get(Student06.class, 1001);
        System.out.println(student1);

        System.out.println("********* fetch book with id 101 **********");

        String hqlQuery1 = "FROM Boook06 b WHERE b.id = 101";
        Boook06 book1 =  session.createQuery(hqlQuery1, Boook06.class).uniqueResult();
        System.out.println(book1);

        System.out.println("********* fetch books of specific student  with HQL**********");

        String hqlQuery2 = "SELECT b.id, b.name FROM Student06 s INNER JOIN s.bookList b WHERE s.id=1001 ";
        //without alias: Student06.bookList.id
        List<Object[]> restultList2 = session.createQuery(hqlQuery2).getResultList();

        restultList2.forEach(obj-> System.out.println(Arrays.toString(obj)));


        System.out.println("********* fetch books of specific student with get method **********");
        Student06 student2 = session.get(Student06.class, 1001);
        System.out.println(student2.getBookList());


        tx.commit();
        session.close();
        sf.close();
    }
}
