package com.cursoudemy.persons.models.dto.dtoV01;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "firstName", "lastName", "gender", "address" })
public record PersonDto(Long id, @JsonProperty("first_name") String firstName,
        @JsonProperty("last_name") String lastName, String address, Character gender) {

}
