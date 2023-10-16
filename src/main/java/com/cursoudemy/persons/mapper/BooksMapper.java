package com.cursoudemy.persons.mapper;

import org.mapstruct.Mapper;

import com.cursoudemy.persons.dto.dtoV01.BookDTO;
import com.cursoudemy.persons.models.Book;

@Mapper(componentModel = "spring")
public interface BooksMapper {
    BookDTO toDto(Book entity);

    Book toEntity(BookDTO dto);
}
