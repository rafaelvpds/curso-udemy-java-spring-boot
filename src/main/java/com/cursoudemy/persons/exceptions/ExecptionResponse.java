package com.cursoudemy.persons.exceptions;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExecptionResponse {

    private Date timestamp;
    private String mensage;
    private String datail;
    

}
