package com.cursoudemy.persons.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cursoudemy.persons.dto.dtoV01.BookDTO;
import com.cursoudemy.persons.models.Book;
import com.cursoudemy.persons.models.Person;
import com.cursoudemy.persons.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/books")
@Tag(name = "Books", description = "Endipoints for Managing Books")
public class BoockController {

        @Autowired
        private BookService bookService;

        @PostMapping
        @Operation(summary = "Add a new Books", description = " Adds a new Books by passing in a Json represatation of the Books", tags = {
                        "Books" }, responses = {
                                        @ApiResponse(description = "Success", responseCode = "200", content = {
                                                        @Content(schema = @Schema(implementation = Book.class))
                                        }),
                                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
                        })
        public BookDTO created(@RequestBody BookDTO bookDTO) {

                return bookService.created(bookDTO);

        }

        @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
        @Operation(summary = "Find All Books", description = " Find All Books", tags = { "Books" }, responses = {
                        @ApiResponse(description = "Success", responseCode = "200", content = {
                                        @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = Person.class)))
                        }),
                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Not Found", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
        }

        )
        public List<BookDTO> findAll() {
                return bookService.getAll();
        }

        @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE,
                        "application/x-yaml" })
        @Operation(summary = "Find a Books", description = " Find a Books", tags = { "Books" }, responses = {
                        @ApiResponse(description = "Success", responseCode = "200", content = {
                                        @Content(schema = @Schema(implementation = Person.class))
                        }),
                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                        @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Not Found", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
        })

        public BookDTO findById(@PathVariable(value = "id") Long id) {
                return bookService.findById(id);
        }

        @PutMapping(value = "/{id}")
        @Operation(summary = "Update a Person", description = " Update a Person by passing in a Json represatation of the person", tags = {
                        "Books" }, responses = {
                                        @ApiResponse(description = "Success", responseCode = "200", content = {
                                                        @Content(schema = @Schema(implementation = Person.class))
                                        }),
                                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Not Found", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
                        })
        public BookDTO update(@PathVariable(value = "id") Long id, @RequestBody BookDTO bookDTO) {

                return bookService.update(id, bookDTO);
        }

        @DeleteMapping(value = "/{id}")
        @Operation(summary = "Delete a Books", description = "Delete a Books", tags = { "Books" }, responses = {
                        @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Not Found", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
        })

        public void delete(@PathVariable(value = "id") Long id) {
                bookService.delete(id);
        }
}
