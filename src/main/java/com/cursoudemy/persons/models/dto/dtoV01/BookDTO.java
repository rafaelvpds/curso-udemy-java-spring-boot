package com.cursoudemy.persons.models.dto.dtoV01;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "id", "author", "launchDate", "price", "title" })
public class BookDTO extends RepresentationModel {
    private Long id;
    @JsonProperty("author")
    private String author;
    @JsonProperty("launchDate")
    private Date launch_date;
    private Double price;
    private String title;
}
