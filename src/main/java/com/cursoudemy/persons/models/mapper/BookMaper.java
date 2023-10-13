package com.cursoudemy.persons.models.mapper;

import org.mapstruct.Mapper;

import com.cursoudemy.persons.models.Book;
import com.cursoudemy.persons.models.dto.dtoV01.BookDTO;

@Mapper(componentModel = "spring")
public interface BookMaper {
    BookDTO toDto(Book entity);

    Book toEntity(BookDTO dto);

}
