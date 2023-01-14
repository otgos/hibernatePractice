package com.hb04.bi_onetoone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.List;

public class RunnerFetch04 {
    public static void main(String[] args) {

        Configuration con = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Student04.class).
                addAnnotatedClass(Diary04.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        // getStudent By Id

        Student04 student04 = session.get(Student04.class, 1003);
        Diary04 diary04 = session.get(Diary04.class, 103);
        System.out.println("********* student obj ***********");
        System.out.println(student04);
        System.out.println("********* diary obj ***********");
        System.out.println(diary04);
        System.out.println("********* diary over student ***********");
        System.out.println(student04.getDiary());
        System.out.println("********** student over diary **********");
        System.out.println(diary04.getStudent());

        System.out.println("********** get Inner Join **********");

        String hqlQuery = "SELECT s.name, d.name FROM Student04 s INNER JOIN FETCH Diary04 d ON s.id = d.student";
        //sql version
        //SELECT s.std_name, d.name from t_student04 s inner join t_diary04 d on s.id = d.std_id

        List<Object[]> resultList  = session.createQuery(hqlQuery).getResultList();
        for(Object[] obj : resultList){
            System.out.println(Arrays.toString(obj));
        }

        System.out.println("********** get Inner Join with Lambda expression **********");
        resultList.forEach(obj->{
            System.out.println(Arrays.toString(obj));
        });

        // !!!Lef join
        //fetch all students and students with book
        //we want book information of students those who have books

        System.out.println("********** get Left Join with **********");
        String hqlQuery2 = "SELECT s.name, d.name FROM Student04 s LEFT JOIN FETCH Diary04 d ON s.id = d.student";
        List<Object[]> resultList2 = session.createQuery(hqlQuery2).getResultList();

        resultList2.forEach(obj->{
            System.out.println(Arrays.toString(obj));
        });

        // !!!Right join
        //fetch all diaries and students with diary(students who have no diary will not be fetched)
        //we want book information of students those who have books

        System.out.println("********** get Right Join with **********");
        String hqlQuery3 = "SELECT s.name, d.name FROM Student04 s RIGHT JOIN FETCH Diary04 d ON s.id = d.student";
        List<Object[]> resultList3 = session.createQuery(hqlQuery3).getResultList();
        resultList3.forEach(obj->{
            System.out.println(Arrays.toString(obj));
        });

        // !!!Full join


        System.out.println("********** get Full  Join with **********");
        String hqlQuery4 ="SELECT s.name, d.name FROM Student04 s FULL JOIN FETCH Diary04 d ON s.id = d.student.id";
        List<Object[]> resultList4 = session.createQuery(hqlQuery4).getResultList();
        resultList4.forEach(obj->{
            System.out.println(Arrays.toString(obj));
        });
        tx.commit();
        sf.close();
        session.close();
    }
}
