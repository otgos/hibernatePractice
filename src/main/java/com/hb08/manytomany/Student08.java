package com.hb08.manytomany;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="t_student08")
public class Student08 {
    @Id
    private int id;
    @Column(name = "std_name", nullable = false)
    private String name;
    private int grade;

    @ManyToMany
    //owner side
    @JoinTable(name="student08_book08", //name of new table
            joinColumns = {@JoinColumn(name="sdt_id")}, // name of column of owner side
            inverseJoinColumns = {@JoinColumn(name = "book_id")})  // column name of other side
    private List<Book08> bookList = new ArrayList<>();

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

    public List<Book08> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book08> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return "Student08{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                ", bookList=" + bookList +
                '}';
    }
}
