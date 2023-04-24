package com.hb12.caching;

/*
    1) First level Cache --->
            * default caching, can not be closed
            * caches in the same session
            * memory is cleared when session is closed

     2) Second level Cache --->
            * closed by default (needs to be enabled manually)
            * caches in Session Factory level (can cache in different sessions)
     3) Query Cache
            * Used for queries
            *needs to be enabled manually


 */

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import org.hibernate.annotations.Cache;

@Entity
@Table(name="t_student12")

@Cacheable
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE, region = "Student12")

public class Student12 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "std_name", nullable = false)
    private String name;
    private int grade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public void setGrade(int mathGrade) {
        this.grade = mathGrade;
    }

    @Override
    public String toString() {
        return "Student12{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mathGrade=" + grade +
                '}';
    }
}
