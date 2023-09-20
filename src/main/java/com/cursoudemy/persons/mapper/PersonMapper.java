package com.cursoudemy.persons.mapper;

import org.springframework.stereotype.Component;

import com.cursoudemy.persons.models.Person;
import com.cursoudemy.persons.models.dto.PersonDto;

@Component
public class PersonMapper {
    public PersonDto toDto(Person person) {

        if (person == null) {
            return null;
        }

        return new PersonDto(person.getId(), person.getFirstName(), person.getLastName(), person.getAddress(),
                person.getGender());
    }

    public Person toEntity(PersonDto personDto) {
        if (personDto == null) {
            return null;
        }
        var entity = new Person();
        entity.setId(personDto.id());
        entity.setFirstName(personDto.firstName());
        entity.setLastName(personDto.lastName());
        entity.setAddress(personDto.address());
        entity.setGender(personDto.gender());

        return entity;
    }

}
