package com.cursoudemy.persons.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursoudemy.persons.models.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
