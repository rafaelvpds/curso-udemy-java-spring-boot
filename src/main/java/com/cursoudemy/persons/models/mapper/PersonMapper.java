package com.cursoudemy.persons.models.mapper;

import org.mapstruct.Mapper;

import com.cursoudemy.persons.models.Person;
import com.cursoudemy.persons.models.dto.dtoV01.PersonDTO;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonDTO toDto(Person entity);

    Person toEntity(PersonDTO dto);

}
