package com.cursoudemy.persons.service;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursoudemy.persons.exceptions.RessourcePersonNotFaundExceptions;
import com.cursoudemy.persons.mapper.PersonMapper;
import com.cursoudemy.persons.models.Person;
import com.cursoudemy.persons.models.dto.PersonDto;
import com.cursoudemy.persons.repository.PersonRepository;

@Service
public class PersonService implements Serializable {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonMapper personMapper;

    public PersonDto created(PersonDto personDto) {
        var personEntity = personMapper.toEntity(personDto);
        var personSave = personRepository.save(personEntity);
        var personDtos = personMapper.toDto(personSave);
        return personDtos;
    }

    public List<PersonDto> findAll() {
        var listPerson = personRepository.findAll().stream().map(personMapper::toDto).collect(Collectors.toList());

        return listPerson;
    }

    public PersonDto findById(Long id) {
        var entity = personRepository.findById(id)
                .orElseThrow(() -> new RessourcePersonNotFaundExceptions("Person Not faund"));
        var personDto = personMapper.toDto(entity);

        return personDto;

    }

    public PersonDto update(Long id, PersonDto person) {

        var entity = personRepository.findById(id)
                .orElseThrow(() -> new RessourcePersonNotFaundExceptions("Person Not faund"));

        entity.setFirstName(person.firstName());
        entity.setLastName(person.lastName());
        entity.setAddress(person.address());
        entity.setGender(person.gender());

        var personSave = personRepository.save(entity);

        var personDto = personMapper.toDto(personSave);

        return personDto;

    }

}
