package com.hb05.manytoone;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="t_student05")

public class Student05 {
    @Id
    private int id;
    @Column(name="std_name", nullable = false)
    private String name;
    private int grade;

    private LocalDateTime createdOn;// created date;

    @ManyToOne
    @JoinColumn(name ="univ_id")
    private University university; //default "university_id"

    @PrePersist //when you save object to database this will work
    public void prePersist(){
        createdOn = LocalDateTime.now();
    }
    //Getter

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

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    //it will be created when you save.. and cannot be changed again
//    public void setCreatedOn(LocalDateTime createdOn) {
//        this.createdOn = createdOn;
//    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    @Override
    public String toString() {
        return "Student05{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                ", createdOn=" + createdOn +
                ", university=" + university +
                '}';
    }
}
