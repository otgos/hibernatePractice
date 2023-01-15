package com.hb08.manytomany;


import com.hb07.bi_onetomany.Book07;
import com.hb07.bi_onetomany.Student07;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class RunnerSave08 {

    public static void main(String[] args) {
        Student08 student1 = new Student08();
        student1.setId(1001);
        student1.setName("AAA");
        student1.setGrade(98);

        Student08 student2 = new Student08();
        student2.setId(1002);
        student2.setName("BBB");
        student2.setGrade(98);

        Student08 student3 = new Student08();
        student3.setId(1003);
        student3.setName("CCC");
        student3.setGrade(98);

        //create 5 book object
        Book08 book1 = new Book08();
        book1.setId(101);
        book1.setName("Math Book");

        Book08 book2 = new Book08();
        book2.setId(102);
        book2.setName("Phys Book");

        Book08 book3 = new Book08();
        book3.setId(103);
        book3.setName("Chemistry Book");

        //instead of setting books to students one by one,  create  arrayList to store books and set it to students
        List<Book08> bookList1 = new ArrayList<>();
        bookList1.add(book1);
        bookList1.add(book2);
        student1.setBookList(bookList1); //set arrayList
        student1.getBookList().add(book3); //add books one by one

        List<Book08> bookList2 = new ArrayList<>();
        bookList1.add(book2);
        bookList1.add(book3);
        student2.setBookList(bookList2);

        List<Book08> bookList3 = new ArrayList<>();
        bookList1.add(book1);
        bookList1.add(book3);
        student3.setBookList(bookList3);


        Configuration con = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Student08.class).
                addAnnotatedClass(Book08.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();


        session.save(student1);
        session.save(student2);
        session.save(student3);

        session.save(book1);
        session.save(book2);
        session.save(book3);


        tx.commit();
        session.close();
        sf.close();
    }


}
