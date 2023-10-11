package com.cursoudemy.persons.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.cursoudemy.persons.models.Person;
import com.cursoudemy.persons.models.dto.dtoV01.PersonDto;
import com.cursoudemy.persons.models.dto.dtoV01.PersonDtoV2;

@Mapper(componentModel = "spring")
public interface PersonsMapper {

    PersonDto toDto(Person entity);

    PersonDtoV2 toDtoV2(Person entity);

    Person toEntity(PersonDto dto);

    Person toEntityV2(PersonDtoV2 dto);

}
