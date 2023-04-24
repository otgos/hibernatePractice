package com.hb11.creteriaapi;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Random;

public class RunnerFetch11 {
    public static void main(String[] args) {
        Configuration con = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Student11.class);
        //addAnnotatedClass(Book09.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        /*

            azirge dein biz minalardi kordk
            CRUD (Create, Read, Update, Delete)
                C-->session.save()
                R-->session.get(), HQL, SQL
                U-->session.update, updateQuery
                D-->session.delete, HQL, SQL
         */


        //show students how to use update method
        //1. using update()

//        Student11 std = session.get(Student11.class, 1L);
//        System.out.println("before update: "+std);
//        std.setName("Updated Student 1");
//        session.update(std);
//        System.out.println("after update update: "+std);


        //2.way using query
        //set grades that are less than 75 to 80
        int sGrade = 80; //make this dynamic..
        int lGrade = 75;

        /*
        String hdqQuery="UPDATE Student11 s SET s.grade =:sMath WHERE  s.grade<:lMath";

        Query query = session.createQuery(hdqQuery); // returns Query from hibernate
        query.setParameter("sMath", sGrade); // we need to set parameters
        query.setParameter("lMath", lGrade);

        int updateRows = query.executeUpdate(); //returns int number of  updated rows
        System.out.println("updated "+updateRows);

         */

        // criteriamen jumis istesek isteuge tiis osi 3 line code bar

        CriteriaBuilder cb = session.getCriteriaBuilder(); //call criteria builder to build criter
        CriteriaQuery<Student11> criteriaQuery = cb.createQuery(Student11.class); //return Generic CriteriaQuery
        Root<Student11> root =  criteriaQuery.from(Student11.class); // above code is to get ROOT

        //until now we wrote boiler plate codes.. this is how they can set up the criteria

        //example 1 select all students
//        criteriaQuery.select(root);
//        Query<Student11> query1 = session.createQuery(criteriaQuery);
//        List<Student11> resultList =  query1.getResultList();
//        resultList.forEach(std-> System.out.println(std));

        //example 2

        //get student whose name is: "Student Name 6"
//        criteriaQuery.select(root). //SELECT * FROM student
//                where(cb.equal(root.get("name"), "Student Name 6" )); //adding WHERE condition
//
//        Query<Student11> query2 = session.createQuery(criteriaQuery);
//        List<Student11> resultList2 = query2.getResultList();
//        resultList2.forEach(s-> System.out.println(s));

        //example 3
        //fetch students whose grade is > 80

//        criteriaQuery.select(root). // all queries are created based on root
//                where(cb.greaterThan(root.get("grade"), 80));
//        Query<Student11> query3 = session.createQuery(criteriaQuery);
//        List<Student11> resultList3 = query3.getResultList();
//        resultList3.forEach(System.out::println);

        //example 4

        //task: fetch students whose grade is less than 95
//        criteriaQuery.select(root). // all queries are created based on root
//                where(cb.lessThan(root.get("grade"), 95));
//
//        Query<Student11> query4 = session.createQuery(criteriaQuery);
//        List<Student11> resultList4 = query4.getResultList();
//        resultList4.forEach(System.out::println);


        //Example 5--> find records whose id =1, or grade is greater than 75

        Long id= 1L;
        Predicate predicateForId = cb.equal(root.get("id"), id);
        Predicate predicateGrade = cb.greaterThan(root.get("grade"), 75);

        Predicate predicateQuery = cb.or(predicateForId, predicateGrade);
        criteriaQuery.where(predicateQuery);
        Query<Student11> query5 = session.createQuery(criteriaQuery);

        List<Student11> resultList5 = query5.getResultList();
        resultList5.forEach(std-> System.out.println(std));


        tx.commit();
        session.close();
        sf.close();

    }
}
