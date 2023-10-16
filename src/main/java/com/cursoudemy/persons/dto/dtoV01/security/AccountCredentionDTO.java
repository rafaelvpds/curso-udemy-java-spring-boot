package com.cursoudemy.persons.dto.dtoV01.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountCredentionDTO {

    private String userName;
    private String password;

}
