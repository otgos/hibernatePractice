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

//        System.out.println("********** using get method**********");
//
//        Student07 student1 = session.get(Student07.class, 1001);
//        //System.out.println(student1.getBookList());
//        System.out.println("Std: "+student1);
//        student1.getBookList().forEach(System.out::println); //method reference
//        student1.getBookList().forEach(std-> System.out.println(std));//lambda expression
//        System.out.println(session.get(Book07.class, 101));
//
//
//        System.out.println("********** fetch students with Book name WITH SQL **********");
//
//        String sqlQuery = "SELECT s.std_name, b.name FROM t_student07 s INNER JOIN t_book07 b ON s.id = b.student_id";
//
//        List<Object[]> reslultList1 = session.createSQLQuery(sqlQuery).getResultList();
//
//        for(Object[] obj: reslultList1){
//            System.out.println(Arrays.toString(obj));
//        }
//
//        System.out.println("********** fetch students with Book name WITH HQL **********");
//
//        String hqlQuery = "SELECT s.name, b.name FROM Student07 s INNER JOIN Book07 b ON s.id = b.student.id";
//
//        List<Object[]> resultList2 = session.createQuery(hqlQuery).getResultList();
//
//        for(Object[] obj: resultList2){
//            System.out.println(Arrays.toString(obj));
//        }
//        //Delete Book07 records with SQL
//        System.out.println("********** DELETE FROM student  with  SQL **********");
//        String sqlQuery2 = "DELETE FROM t_book07";

        //int numbOfDeletedRecords =  session.createSQLQuery(sqlQuery2).executeUpdate(); //returns the number of updates
       // System.out.println("Deleted record number: "+numbOfDeletedRecords);

        //task to student: Delete students records with SQL

//        String sqlQuery3 = "DELETE FROM t_student07";
//        int numbOfDeletedRecords1 =  session.createSQLQuery(sqlQuery3).executeUpdate(); //returns the number of updates
//        System.out.println("Deleted record number: "+numbOfDeletedRecords1);

        //task: Delete Book records with HQL

//        String hqlQuery2 = "DELETE FROM Book07";
//        int numOfDeletesByHql = session.createQuery(hqlQuery2).executeUpdate();
//        System.out.println("numOfDeletesByHql: "+numOfDeletesByHql);

        //task: Delete Student records with HQL

//        String hqlQuery3 = "DELETE FROM Student07";
//        int numOfDeletesByHql1 = session.createQuery(hqlQuery3).executeUpdate();
//        System.out.println("numOfDeletesByHql1: "+numOfDeletesByHql1);

        //task delete record named "Art Book" from Book07 class,
//        String hqlQuery4 = "DELETE FROM Book07 b WHERE b.name = 'Art Book'";
//        int numbOfDeletedBook =  session.createQuery(hqlQuery4).executeUpdate();
//        System.out.println("numbOfDeletedBook: "+numbOfDeletedBook);

        /*
        Note: If there is parent child relation in tables, you cannot delete parent class directly.
        there are 2 ways
         first: first delete all child classes and then delete parent class (But it is not a practical way.. you will do 2 or more steps to delete a record)
        second: write Cascade / orphanRemoval in Student entity's related field

         */

        //Delete Student object whose id is 1001 using delete Method

//        Student07 student = session.get(Student07.class, 1001);
//        session.delete(student);

        //When orphanRemoval = true and when you run student.getBookList().set(0, null), it will delete child class which is at 0th index
        // this is the main difference between cascade and orphan removal

        //Task: write a HQL query which will bring books whose name has word "Java"
        String hqlQuery4 = "SELECT s FROM Student07 s JOIN s.bookList b WHERE b.name LIKE '%Java%'";
        List<Student07> resultList = session.createQuery(hqlQuery4, Student07.class).getResultList();
        for(Student07 student07: resultList){
            System.out.println(student07);
        }


        tx.commit();
        session.close();
        sf.close();
    }
}
