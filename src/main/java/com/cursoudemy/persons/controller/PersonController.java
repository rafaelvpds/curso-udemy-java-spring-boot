package com.cursoudemy.persons.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cursoudemy.persons.models.dto.dtoV01.PersonDtoV2;
import com.cursoudemy.persons.service.PersonService;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping
    public PersonDtoV2 created(@RequestBody PersonDtoV2 personDto) {

        return personService.created(personDto);

    }

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml" })
    public List<PersonDtoV2> findAll() {
        return personService.getAll();
    }

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE,
            "application/x-yaml" })

    public PersonDtoV2 findById(@PathVariable(value = "id") Long id) {
        return personService.findById(id);
    }

    @PutMapping(value = "/{id}")
    public PersonDtoV2 update(@PathVariable(value = "id") Long id, @RequestBody PersonDtoV2 personDto) {

        return personService.update(id, personDto);
    }

    @DeleteMapping(value = "/{id}")

    public void delete(@PathVariable(value = "id") Long id) {
        personService.delete(id);
    }
}
