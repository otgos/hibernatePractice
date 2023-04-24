package com.hb14.entity_life_cycle.life_cycle2;

import javax.persistence.*;

@Entity
@Table(name="t_emplyee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="employee_id")
    private Long id;
    @Column(name="empl_name")
    private String name;
    @Column(name="empl_salary")
    private Double salary;

    //Entity lifecycle
    @PrePersist
    public void onPrepersist(){
        System.out.println("Before data being persist");
    }

    @PostPersist
    public void onPostPersist(){
        System.out.println("Before data being persist");
    }

    @PostLoad
    public void onPostLoad (){
        System.out.println("Before data being persist");
    }
    @PreRemove
    public void onPreRemove()
    {
        //before remove what ever you want.. like remove child
        System.out.println("Before data being persist");

    }

    @PostRemove
    public void onPostRemove()
    {
        //before remove what ever you want.. like remove child
        System.out.println("Before data being persist");

    }

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

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    //toString

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
