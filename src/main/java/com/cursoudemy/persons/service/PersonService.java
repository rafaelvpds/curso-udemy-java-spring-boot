package com.cursoudemy.persons.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursoudemy.persons.exceptions.RessourcePersonNotFaundExceptions;
import com.cursoudemy.persons.mapper.PersonMapperV01;
import com.cursoudemy.persons.models.dto.dtoV01.PersonDto;
import com.cursoudemy.persons.repository.PersonRepository;

@Service
public class PersonService implements Serializable {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonMapperV01 personMapper;

    public PersonDto created(PersonDto personDto) {

        return personMapper.toDto(personRepository.save(personMapper.toEntity(personDto)));
    }

    public List<PersonDto> getAll() {

        return personMapper.mapDto(personRepository.findAll());
    }

    public PersonDto findById(Long id) {
        var entity = personRepository.findById(id)
                .orElseThrow(() -> new RessourcePersonNotFaundExceptions("Person Not faund"));

        return personMapper.toDto(entity);

    }

    public PersonDto update(Long id, PersonDto person) {

        var entity = personRepository.findById(id)
                .orElseThrow(() -> new RessourcePersonNotFaundExceptions("Person Not faund"));

        entity.setFirstName(person.firstName());
        entity.setLastName(person.lastName());
        entity.setAddress(person.address());
        entity.setGender(person.gender());

        var personSave = personRepository.save(entity);
        return personMapper.toDto(personSave);

    }

}
