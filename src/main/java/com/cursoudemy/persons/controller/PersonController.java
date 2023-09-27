package com.cursoudemy.persons.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cursoudemy.persons.models.dto.dtoV01.PersonDto;
import com.cursoudemy.persons.service.PersonService;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping
    public PersonDto created(@RequestBody PersonDto personDto) {

        return personService.created(personDto);

    }

    @GetMapping
    public List<PersonDto> findAll() {
        return personService.getAll();
    }

    @GetMapping("/{id}")
    public PersonDto findById(@PathVariable(value = "id") Long id) {
        return personService.findById(id);
    }

    @PutMapping("/{id}")
    public PersonDto update(@PathVariable(value = "id") Long id, @RequestBody PersonDto personDto) {

        return personService.update(id, personDto);
    }

}
