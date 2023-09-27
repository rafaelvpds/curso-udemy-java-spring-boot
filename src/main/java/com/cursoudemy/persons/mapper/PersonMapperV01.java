package com.cursoudemy.persons.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.cursoudemy.persons.models.Person;
import com.cursoudemy.persons.models.dto.dtoV01.PersonDto;

@Mapper(componentModel = "spring")
public interface PersonMapperV01 {

    PersonDto toDto(Person entity);

    List<PersonDto> mapDto(List<Person> entity);

    Person toEntity(PersonDto dto);

    List<Person> mapEntity(List<PersonDto> dtos);
}
