package com.hb14.entity_life_cycle.life_cycle2;

import com.hb14.entity_life_cycle.Student14;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RunnerSaveEmpl {
    public static void main(String[] args) {

        Employee employee1 = new Employee();
        employee1.setName("AAA");
        employee1.setSalary(988.9);
        Configuration con = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Employee.class);

        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        System.out.println("--------------session.save");
        session.save(employee1);
        System.out.println("************get obj");

        Employee employee = session.get(Employee.class, 1L);


        System.out.println("++++++++++++=Remove");
        session.delete(employee);


        tx.commit(); //persistent state


        session.close();
        sf.close();
    }
}
