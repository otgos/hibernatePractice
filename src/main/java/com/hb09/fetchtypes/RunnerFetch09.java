package com.hb09.fetchtypes;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RunnerFetch09 {
    public static void main(String[] args) {
        Configuration con = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Student09.class).
                addAnnotatedClass(Book09.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();


        //use get and delete methods

        //Student09 student09 = session.get(Student09.class, 1002); // book3 - book4 are assigned
        //see the console and explain that there is select query for no book
       // System.out.println(student09); //try with this....

        // try this
        //session.delete(student09); // check console and try to understand that there is select query for book and check pgadmin

        // delete all data and run save again

        /*
            1. if next side is Many then default is Lazy
                OneToMany
                ManyToMany

            2. If next side is One then default is EAGER
                ManyToOne
                OneToOne
         */


        //change fetchType to Eager on Student class and check only
        //Student09 student09 = session.get(Student09.class, 1002); // book3 - book4 are assigned
        // in student09 obj we have all data of student and book

        //fetch type change to Lazy by taking in into comment

        // fetch Book using get and ask if it is coming in EAGR or Lazy
       // Book09 book09 = session.get(Book09.class, 101); // it is eager //comment student

        // if i dont wand info about student we need to change this Lazy


        Student09 student09 = session.get(Student09.class, 1002); // select is running twice



        tx.commit();
        session.close();
        sf.close();
// because this data is saved RAM.. so we need to know what/where
        for (Book09 b : student09.getBookList()){
            System.out.println(b);
        }
    }
}
