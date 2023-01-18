package com.hb11.creteriaapi;

import com.hb10.idgeneration.Student10;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;
import java.util.Random;


public class RunnerSave11 {

    public static void main(String[] args) {
        Configuration con = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Student11.class);
        //addAnnotatedClass(Book09.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Random random = new Random();

        for(int i =1; i<=20; i++){
            Student11 student = new Student11();
            student.setName("Student Name "+i); //create 20 students
            student.setGrade(random.nextInt(100)); //assign random score

            session.save(student);
        }

        tx.commit();
        session.close();
        sf.close();
    }
}
