package com.hb13.get_load;

import com.hb12.hibernatecaching.Student12;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RunnerFetch13 {
    public static void main(String[] args) {
        Configuration con = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Student13.class);

        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        System.out.println("•••••••••• get method starts •••••••••");
        Student13 student1 =  session.get(Student13.class, 1L);
        //explain: this will give us student object with all its fields


        System.out.println("Student name: "+student1.getName());
        System.out.println("Student grade: "+student1.getGrade());

        //explain that there is no other select query for getting name and grade, because we have ready data

        System.out.println("•••••••••• get method ends •••••••••");
        System.out.println("•••••••••• load method starts •••••••••");

        Student13 student2 = session.load(Student13.class, 1L); //ask? will i see select query??

        //answer: reasons-- 1 maybe because of cache or load?

        Student13 student3 = session.load(Student13.class, 2L); //ask? will i see select query??
        System.out.println("getName starts");
        System.out.println("Student get name: "+student3.getName());
        System.out.println("getName ends");

        //explain : we requested name but it brought all object


        //object which is null DB
        //get()
        System.out.println("get() starts here..... for null value.....");
        Student13 student4 = session.get(Student13.class, 5L);
        System.out.println("Student whose id is 5: "+student4);
        System.out.println("get() ends here..... for null value.....");


        //load()
        System.out.println("load() starts here..... for null value.....");
        Student13 student5 = session.load(Student13.class, 5L);
        System.out.println("Student whose id is 5: "+student5);
        System.out.println("load() ends here..... for null value.....");

        /*
            1. if you are sure that data exists in DB, we can use load method
            2. if you are going to use data directly like:  getName etc... we need to use get method

         */

        // why we need load method??????????

        //check below code
        //i want to delete data.......
        Student13 student6 = session.get(Student13.class, 2L);
        session.delete(student6);

        //vs

        Student13 student7 = session.load(Student13.class, 2L);
        session.delete(student7);

        // it is better to delete object by load... because we will get reference of object and
        // delete without making select query



        tx.commit();
        session.close();
        sf.close();
    }
}
