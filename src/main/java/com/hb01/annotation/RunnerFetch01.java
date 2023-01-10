package com.hb01.annotation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.List;

public class RunnerFetch01 {
    public static void main(String[] args) {
        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student01.class);
        //Let Hibernate know that my configuration and Entity class
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        //Inorder fetch data from DB there 3 ways
        //1)get()
        //2 SQL query
        //HQL
        //1st way
//        Student01 student01 = session.get(Student01.class, 1001);
//        Student01 student02 = session.get(Student01.class, 1001);
//        Student01 student03 = session.get(Student01.class, 1001);
//
//
//        System.out.println(student01); //sends toString method
//        System.out.println(student02); //sends toString method
//        System.out.println(student03); //sends toString method
//        // 2nd Way
//
//        String sqlQuery="SELECT * FROM t_student01";
//        List<Object[]> resultList = session.createSQLQuery(sqlQuery).getResultList();
//
//        for(Object[] obj :resultList){
//            System.out.println(Arrays.toString(obj));
//        }
        //3rd way
        //Trick:IN HQL query after FROM we need to class name
        String hqlQuery = "FROM Student01";
        List<Student01> resultList2 = session.createQuery(hqlQuery, Student01.class).getResultList();
        for (Student01 student01:resultList2){
            System.out.println(student01);
        }

        // if you want to fetch unique data uniqueResult()
        tx.commit(); //info will not be sent to DB

        session.close();
        sf.close();
    }
}
