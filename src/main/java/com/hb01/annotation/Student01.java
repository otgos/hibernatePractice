package com.hb01.annotation;

import javax.persistence.*;

@Entity // converts class to db table
@Table(name="t_student01")//changed table name to "t_student01"
public class Student01 {//creates table called "student01" in db
    @Id //specifies the primary key
    //@Column(name="std_id")
    private int id;
    //Column is not must, but it will give come direction to db
    @Column (name = "stu_name", length = 100, nullable = false, unique = false)
    private String name;
    //@Transient //this colulumn will not be created in DB
    private int grade;
//    @Lob //large object
//    private byte[] image;

    //getter  - setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    //to string
    @Override
    public String toString() {
        return "Student01{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }
}
