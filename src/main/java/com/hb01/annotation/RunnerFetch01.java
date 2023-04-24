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
        //2 SQL query (native)
        //HQL
        //1st way
        //will run sql query 3 times...
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
        //Trick:IN HQL query after FROM we need to class name and it is case sensitive
        String hqlQuery1 = "FROM Student01";
        List<Student01> resultList2 = session.createQuery(hqlQuery1, Student01.class).getResultList();


        for (Student01 student01:resultList2){
            System.out.println(student01);
        }



        // if you want to fetch unique data uniqueResult()
        //******************************uniqResult() with SQL*****************
        String sqlQuery1 = "SELECT * FROM t_student01 WHERE student_name = 'XXX'";
        //we will fetch one object but the fields or columns are will come as array

        Object[] uniqResult = (Object[]) session.createQuery(sqlQuery1).uniqueResult();
        System.out.println(Arrays.toString(uniqResult));
        System.out.println(uniqResult[0]+": "+uniqResult[1]+": " +uniqResult[2]);

        //******************************uniqResult() with HQL*****************

        String hqlQuery2 = "FROM Student01 WHERE name = name='XXX'";
        Student01 uniqueResult02 = session.createQuery(hqlQuery2,Student01.class).uniqueResult();
        System.out.println(uniqueResult02);

        //******************************Alias with HQL*****************
        String hqlQuery3 ="FROM Student01 std WHERE std.name = 'XXX'";
        Student01 student03 = session.createQuery(hqlQuery3, Student01.class).uniqueResult();
        System.out.println(student03);

        //******************************Get obj s grade is 90 *****************
        String hqlQuery4 = "SELECT s.id, s.name FROM Student01 s WHERE s.grade = 90"; //Student01 = s
        //we did not mapp our object to Student01 in createQuery(). So we assigned into Object[] container
        List<Object[]> resultList3 = session.createQuery(hqlQuery4).getResultList();
        for(Object[] o: resultList3){
            System.out.println(Arrays.toString(o));
        }

        tx.commit(); //info will not be sent to DB

        session.close();
        sf.close();
    }
}
