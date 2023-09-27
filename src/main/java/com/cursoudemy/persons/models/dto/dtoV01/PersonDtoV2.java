package com.cursoudemy.persons.models.dto.dtoV01;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonPropertyOrder({ "id", "firstName", "lastName", "gender", "address" })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDtoV2 extends RepresentationModel<PersonDtoV2> {

        private Long id;
        @JsonProperty("first_name")
        private String firstName;
        @JsonProperty("last_name")
        private String lastName;
        private String address;
        private Character gender;
}