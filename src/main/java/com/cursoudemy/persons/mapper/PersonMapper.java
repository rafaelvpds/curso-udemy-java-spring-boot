package com.cursoudemy.persons.mapper;

import org.mapstruct.Mapper;

import com.cursoudemy.persons.dto.dtoV01.PersonDTO;
import com.cursoudemy.persons.models.Person;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonDTO toDto(Person entity);

    Person toEntity(PersonDTO dto);

}
