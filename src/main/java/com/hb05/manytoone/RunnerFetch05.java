package com.hb05.manytoone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class RunnerFetch05 {
    public static void main(String[] args) {
        Configuration con = new Configuration().
                configure("hibernate.cfg.xml");
        //check configuration and come back
//                addAnnotatedClass(Student05.class).
//                addAnnotatedClass(University.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        //get method
       Student05 student = session.get(Student05.class, 1001);
        System.out.println(student);
        System.out.println(student.getUniversity());

        // fetch students whose univ id is 101 using hql
        System.out.println("******* students whose univ id is 101 ********");
        String hqlQuery1 = "FROM Student05 s WHERE s.university.id = 101";
        //since we are using only Student05 class we can specify object type
        List<Student05> resultList1 = session.createQuery(hqlQuery1, Student05.class).getResultList();
        resultList1.forEach(s->{
            System.out.println(s);
        });


        //explain what is happening in console log.. you wrote get method by backgound lines of sql query running
        tx.commit();
        session.close();
        sf.close();
    }
}
