package com.example.appauthors.db;

import jakarta.persistence.*;
import lombok.Data;



    @Entity
    @Data
    @Table(name = "authors")
    public class Author {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        private Integer id;

        @Column(name="first_name")
        private String firstName;

        @Column(name="last_name")
        private String lastName;
}


