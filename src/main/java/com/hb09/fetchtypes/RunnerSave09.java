package com.hb09.fetchtypes;

import com.hb08.manytomany.Book08;
import com.hb08.manytomany.Student08;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RunnerSave09 {
    public static void main(String[] args) {
        Student09 student1 = new Student09();
        student1.setId(1001);
        student1.setName("AAA");
        student1.setGrade(98);

        Student09 student2 = new Student09();
        student2.setId(1002);
        student2.setName("BBB");
        student2.setGrade(98);

        Student09 student3 = new Student09();
        student3.setId(1003);
        student3.setName("CCC");
        student3.setGrade(98);

        //create 5 book object
        Book09 book1 = new Book09();
        book1.setId(101);
        book1.setName("Math Book");

        Book09 book2 = new Book09();
        book2.setId(102);
        book2.setName("Phys Book");

        Book09 book3 = new Book09();
        book3.setId(103);
        book3.setName("Chemistry Book");

        Book09 book4 = new Book09();
        book4.setId(104);
        book4.setName("Art Book");

        Book09 book5 = new Book09();
        book5.setId(105);
        book5.setName("Hibernate Book");

        //Normally we need to setFrom book side, because our columns is there.
        // since we have used cascadeType = All to set OneToMany relation we have to set from both sides
        student1.getBookList().add(book1);
        student1.getBookList().add(book2);

        student2.getBookList().add(book3);
        student2.getBookList().add(book4);

        student1.getBookList().add(book5);

        // do the setting from book side.. or else related column will be null

        book1.setStudent(student1);
        book2.setStudent(student1);
        book3.setStudent(student2);
        book4.setStudent(student2);
        book5.setStudent(student3);


        Configuration con = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Student09.class).
                addAnnotatedClass(Book09.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();


        session.save(student1);
        session.save(student2);
        session.save(student3);


        // when we are using cascade we do not need to save this/ if you save book you will not get error but needless
        // (if you save/delete parent class, child class will be saved/deleted)
//        session.save(book1);
//        session.save(book2);
//        session.save(book3);
//        session.save(book4);
//        session.save(book5);


        tx.commit();
        session.close();
        sf.close();

    }

}
