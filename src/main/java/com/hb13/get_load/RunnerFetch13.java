package com.hb13.get_load;

/*
     1)   Performance: load() method returns a proxy object without hitting the database,
         which can be more efficient than get() method. This is because the proxy object is created
         using the object's identifier and doesn't contain any data until it's actually accessed.

      2)  Lazy loading: If you use load() method, you can take advantage of
        Hibernate's lazy loading feature, which means that related objects are
        not retrieved from the database until they are accessed. This can help improve
        performance and reduce memory usage.

       3) Consistency: If the object doesn't exist in the database, load() method
        throws an ObjectNotFoundException, whereas get() method returns null. This can help
        ensure consistency in your application by signaling an error if the requested object doesn't exist.

       4) Object state: When you use load() method, you can be sure that the object is in
        a persistent state, which means that it's associated with a Hibernate session.
         This can be useful if you need to modify the object later and save the changes to the database.
 */

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
        System.out.println("•••••••••• get method ends •••••••••");

        System.out.println("Student name: "+student1.getName());
        System.out.println("Student grade: "+student1.getGrade());

        //explain that there is no other select query for getting name and grade, because we have ready data


        System.out.println("•••••••••• load method starts •••••••••");

        Student13 student2 = session.load(Student13.class, 1L); //ask? will i see select query??

        //answer: reasons-- 1 maybe because of cache or load?
        System.out.println("load mthd starts");
        Student13 student3 = session.load(Student13.class, 2L); //ask? will i see select query??


        System.out.println("load mthd ends");
        System.out.println("Student get ID: "+student3.getId()); //**check console
        System.out.println("Student get name: "+student3.getName());
        //explain : we requested name but it brought all object


        //object which is null DB
        //get()
        System.out.println("get() starts here..... for null value.....");
        Student13 student4 = session.get(Student13.class, 5L);
        if(student4!=null){
            System.out.println("Student whose id is 5: "+student4); //
        }
        System.out.println("get() ends here..... for null value.....");


        //load()
        System.out.println("load() starts here..... for null value.....");
        Student13 student5 = session.load(Student13.class, 5L);
        if(student5!=null){ //actually it is not null, it is fake
            System.out.println("Student whose id is 5: "+student5); //
        }
        //**because obj is dummy or fake obj (it is like empty box)
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
