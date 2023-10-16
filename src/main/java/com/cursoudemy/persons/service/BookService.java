package com.cursoudemy.persons.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursoudemy.persons.controller.BoockController;
import com.cursoudemy.persons.controller.PersonController;
import com.cursoudemy.persons.exceptions.RessourcePersonNotFaundExceptions;
import com.cursoudemy.persons.models.dto.dtoV01.BookDTO;
import com.cursoudemy.persons.models.mapper.BooksMaper;
import com.cursoudemy.persons.repository.BookRepository;

@Service
public class BookService implements Serializable {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BooksMaper bookMaper;

    public BookDTO created(BookDTO bookDTO) {

        BookDTO vo = bookMaper.toDto(bookRepository.save(bookMaper.toEntity(bookDTO)));

        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getId())).withSelfRel());
        return vo;
    }

    public List<BookDTO> getAll() {

        var books = bookRepository.findAll().stream().map(bookMaper::toDto).collect(Collectors.toList());

        books.forEach(b -> b.add(linkTo(methodOn(BoockController.class).findById(b.getId())).withSelfRel()));
        return books;
    }

    public BookDTO findById(Long id) {
        var entity = bookRepository.findById(id)
                .orElseThrow(() -> new RessourcePersonNotFaundExceptions("Person Not faund"));

        BookDTO vo = bookMaper.toDto(entity);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());

        return vo;

    }

    public BookDTO update(Long id, BookDTO bookDTO) {

        var entity = bookRepository.findById(id)
                .orElseThrow(() -> new RessourcePersonNotFaundExceptions("Person Not faund"));

        entity.setAuthor(bookDTO.getAuthor());
        entity.setLaunch_date(bookDTO.getLaunch_date());
        entity.setPrice(bookDTO.getPrice());
        entity.setTitle(bookDTO.getTitle());

        var vo = bookMaper.toDto(bookRepository.save(entity));
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getId())).withSelfRel());

        return vo;

    }

    public void delete(Long id) {

        var person = bookRepository.findById(id)
                .orElseThrow(() -> new RessourcePersonNotFaundExceptions("Person not faund"));
        bookRepository.delete(person);
    }

}
