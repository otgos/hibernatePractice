package com.hb10.idgeneration;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_book10")
public class Book10 {
    @Id
    private int id;
    private String name;
}
