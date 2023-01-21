package com.hb14.entity_life_cycle;

import com.hb12.hibernatecaching.Student12;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RunnerSave14 {
    public static void main(String[] args) {
        Student14 student1 = new Student14(); //transient state -> hibernate has no responsibility

        student1.setName("AAA");
        student1.setGrade(98);

        Student14 student2 = new Student14();

        student2.setName("BBB");
        student2.setGrade(98);

        Student14 student3 = new Student14();

        student3.setName("CCC");
        student3.setGrade(98);


        Configuration con = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Student14.class);

        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();


        session.save(student1); //persistent state

        student1.setName("Updated AAA"); // do i need to save again?

        session.evict(student1); //Detached State. Remove this instance from the session cache.
        // Changes to the instance will not be synchronized with the database.

        session.update(student1); //detached obj will be taken to persistent state
        session.merge(student1);  //detached obj will be taken to persistent state

        tx.commit(); //persistent state

        session.save(student2);
        session.save(student3);

        session.close();
        sf.close();
    }
}
