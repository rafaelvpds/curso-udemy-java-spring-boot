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

import com.cursoudemy.persons.dto.dtoV01.PersonDTO;
import com.cursoudemy.persons.models.Person;
import com.cursoudemy.persons.service.PersonService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

//@CrossOrigin // Se eu defino nada, eu permito todas as requisições para esse cara
@RestController
@RequestMapping("/api/persons")
@Tag(name = "People", description = "Endipoints for Managing People")
public class PersonController {

        @Autowired
        private PersonService personService;

        // @CrossOrigin(origins = { "http://localhost:8080", "http://localhost:3035" })
        @PostMapping
        @Operation(summary = "Add a new Person", description = " Adds a new Person by passing in a Json represatation of the person", tags = {
                        "People" }, responses = {
                                        @ApiResponse(description = "Success", responseCode = "200", content = {
                                                        @Content(schema = @Schema(implementation = Person.class))
                                        }),
                                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
                        })
        public PersonDTO created(@RequestBody PersonDTO personDto) {

                return personService.created(personDto);

        }

        @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
        @Operation(summary = "Find All People", description = " Find All People", tags = { "People" }, responses = {
                        @ApiResponse(description = "Success", responseCode = "200", content = {
                                        @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = Person.class)))
                        }),
                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Not Found", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
        }

        )
        public List<PersonDTO> findAll() {
                return personService.getAll();
        }

        // @CrossOrigin(origins = "http://localhost:8080")
        @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE,
                        "application/x-yaml" })
        @Operation(summary = "Find a People", description = " Find a People", tags = { "People" }, responses = {
                        @ApiResponse(description = "Success", responseCode = "200", content = {
                                        @Content(schema = @Schema(implementation = Person.class))
                        }),
                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                        @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Not Found", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
        })

        public PersonDTO findById(@PathVariable(value = "id") Long id) {
                return personService.findById(id);
        }

        @PutMapping(value = "/{id}")
        @Operation(summary = "Update a Person", description = " Update a Person by passing in a Json represatation of the person", tags = {
                        "People" }, responses = {
                                        @ApiResponse(description = "Success", responseCode = "200", content = {
                                                        @Content(schema = @Schema(implementation = Person.class))
                                        }),
                                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Not Found", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
                        })
        public PersonDTO update(@PathVariable(value = "id") Long id, @RequestBody PersonDTO personDto) {

                return personService.update(id, personDto);
        }

        @DeleteMapping(value = "/{id}")
        @Operation(summary = "Delete a People", description = "Delete a People", tags = { "People" }, responses = {
                        @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Not Found", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
        })

        public void delete(@PathVariable(value = "id") Long id) {
                personService.delete(id);
        }
}
