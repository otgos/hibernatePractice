package com.hb10.idgeneration;

import javax.persistence.*;

/*
        GenerationType.AUTO --> Hibernate decides how to set strategy

        Oracle DB and PostgreSQL --> by default SEQUENCE (developer has some control where to start and how to increase)
        MySQL  and Microsoft SQL  --> IDENTITY(DB controls the ID setting)

        GenerationType.TABLE --> slowest one because it creates seperate table and gets id from that table

 */
@Entity
@Table(name = "t_student10")
public class Student10 {
    @GeneratedValue(generator = "sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="sequence", //should be matching @GeneratedValue's generator attribute
                        sequenceName = "student_seq", // sequence that will be stored in DB
                        initialValue = 1000, //starting number of id
                        allocationSize = 10 //increments 10 by 10


    )
    @Id
    private int id;
    @Column(name = "std_name", nullable = false)
    private String name;
    private int grade;


    public int getId() {
        return id;
    }

//    public void setId(int id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student10{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }
}
