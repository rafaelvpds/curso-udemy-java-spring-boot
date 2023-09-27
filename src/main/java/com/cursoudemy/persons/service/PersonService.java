package com.cursoudemy.persons.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import com.cursoudemy.persons.controller.PersonController;
import com.cursoudemy.persons.exceptions.RessourcePersonNotFaundExceptions;
import com.cursoudemy.persons.mapper.PersonMapperV01;
import com.cursoudemy.persons.models.dto.dtoV01.PersonDtoV2;
import com.cursoudemy.persons.repository.PersonRepository;

@Service
public class PersonService implements Serializable {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonMapperV01 personMapper;

    public PersonDtoV2 created(PersonDtoV2 personDto) {

        PersonDtoV2 vo = personMapper.toDtoV2(personRepository.save(personMapper.toEntityV2(personDto)));

        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getId())).withSelfRel());
        return vo;
    }

    public List<PersonDtoV2> getAll() {

        var persons = personMapper.mapDtoV2(personRepository.findAll());
        persons.stream()
                .forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getId())).withSelfRel()));
        return persons;
    }

    public PersonDtoV2 findById(Long id) {
        var entity = personRepository.findById(id)
                .orElseThrow(() -> new RessourcePersonNotFaundExceptions("Person Not faund"));

        PersonDtoV2 vo = personMapper.toDtoV2(entity);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());

        return vo;

    }

    public PersonDtoV2 update(Long id, PersonDtoV2 person) {

        var entity = personRepository.findById(id)
                .orElseThrow(() -> new RessourcePersonNotFaundExceptions("Person Not faund"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = personMapper.toDtoV2(personRepository.save(entity));
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getId())).withSelfRel());

        return vo;

    }

    public void delete(Long id) {

        var person = personRepository.findById(id)
                .orElseThrow(() -> new RessourcePersonNotFaundExceptions("Person not faund"));
        personRepository.delete(person);
    }

}
