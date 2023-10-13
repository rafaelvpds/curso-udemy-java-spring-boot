package com.cursoudemy.persons.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursoudemy.persons.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
