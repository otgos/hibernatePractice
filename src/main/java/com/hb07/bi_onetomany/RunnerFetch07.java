package com.hb07.bi_onetomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.List;

public class RunnerFetch07 {
    public static void main(String[] args) {
        Configuration con = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Student07.class).
                addAnnotatedClass(Book07.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        System.out.println("********** using get method**********");

        Student07 student1 = session.get(Student07.class, 1001);
        //System.out.println(student1.getBookList());
        System.out.println("Std: "+student1);
        student1.getBookList().forEach(System.out::println); //method reference
        student1.getBookList().forEach(std-> System.out.println(std));//lambda expression
        System.out.println(session.get(Book07.class, 101));


        System.out.println("********** fetch students with Book name WITH SQL **********");

        String sqlQuery = "SELECT s.std_name, b.name FROM t_student07 s INNER JOIN t_book07 b ON s.id = b.student_id";

        List<Object[]> reslultList1 = session.createSQLQuery(sqlQuery).getResultList();

        for(Object[] obj: reslultList1){
            System.out.println(Arrays.toString(obj));
        }

        System.out.println("********** fetch students with Book name WITH HQL **********");

        String hqlQuery = "SELECT s.name, b.name FROM Student07 s INNER JOIN Book07 b ON s.id = b.student";

        List<Object[]> resultList2 = session.createQuery(hqlQuery).getResultList();

        for(Object[] obj: resultList2){
            System.out.println(Arrays.toString(obj));
        }

        tx.commit();
        session.close();
        sf.close();
    }
}
