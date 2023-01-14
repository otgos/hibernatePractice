package com.hb04.bi_onetoone;

import javax.persistence.*;

@Entity
@Table(name="t_student04")
public class Student04 {
    @Id
    private int id;
    @Column(name = "std_name")
    private String name;

    private int grade;

    @OneToOne(mappedBy = "student") //will not create new column in student table, instead use the column created in Diary CLass
    private Diary04 diary;

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

    public Diary04 getDiary() {
        return diary;
    }

    public void setDiary(Diary04 diary) {
        this.diary = diary;
    }

    //when we fetch student and diary there will recursive calling
    // causes to be stack overflow
    @Override
    public String toString() {
        return "Student04{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                //", diary=" + diary +
                '}';
    }
}
