package com.cursoudemy.persons.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "books")
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Book {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Column(nullable = false, length = 180)
    private String author;
    @Setter
    @Column(name = "launchDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date launch_date;
    @Setter
    @Column(nullable = false)
    private Double price;
    @Setter
    @Column(nullable = false, length = 250)
    private String title;

}
