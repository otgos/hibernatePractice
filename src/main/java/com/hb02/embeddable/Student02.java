package com.hb02.embeddable;

import javax.persistence.*;

@Entity
@Table(name="t_student02")
public class Student02 {
    @Id
    private int id;
    @Column (name="student_name", length = 100, unique = false, nullable = false)
    private String name;
    private int grad;
    //@Embedded //optional
    private Address address;

    //Getter- setter

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

    public int getGrad() {
        return grad;
    }

    public void setGrad(int grad) {
        this.grad = grad;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    //toString

    @Override // delete address
    public String toString() {
        return "Student02{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grad=" + grad +
                '}';
    }
}
