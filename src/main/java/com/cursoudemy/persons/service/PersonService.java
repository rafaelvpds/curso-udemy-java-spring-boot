package com.cursoudemy.persons.service;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import com.cursoudemy.persons.controller.PersonController;
import com.cursoudemy.persons.dto.dtoV01.PersonDTO;
import com.cursoudemy.persons.exceptions.RessourcePersonNotFaundExceptions;
import com.cursoudemy.persons.mapper.PersonMapper;
import com.cursoudemy.persons.repository.PersonRepository;

@Service
public class PersonService implements Serializable {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonMapper personMapper;

    public PersonDTO created(PersonDTO personDto) {

        PersonDTO vo = personMapper.toDto(personRepository.save(personMapper.toEntity(personDto)));

        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getId())).withSelfRel());
        return vo;
    }

    public List<PersonDTO> getAll() {

        var persons = personRepository.findAll().stream().map(personMapper::toDto).collect(Collectors.toList());

        persons.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getId())).withSelfRel()));
        return persons;
    }

    public PersonDTO findById(Long id) {
        var entity = personRepository.findById(id)
                .orElseThrow(() -> new RessourcePersonNotFaundExceptions("Person Not faund"));

        PersonDTO vo = personMapper.toDto(entity);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());

        return vo;

    }

    public PersonDTO update(Long id, PersonDTO person) {

        var entity = personRepository.findById(id)
                .orElseThrow(() -> new RessourcePersonNotFaundExceptions("Person Not faund"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = personMapper.toDto(personRepository.save(entity));
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getId())).withSelfRel());

        return vo;

    }

    public void delete(Long id) {

        var person = personRepository.findById(id)
                .orElseThrow(() -> new RessourcePersonNotFaundExceptions("Person not faund"));
        personRepository.delete(person);
    }

}
