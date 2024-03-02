package com.example.appbooks.db;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String isbn;
    private String title;
    private BigDecimal price;
    @Column(name="author_id")
    private Integer authorId;



}
