package com.cursoudemy.persons.dto.dtoV01.security;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TokenDTO {

    private String userName;
    private Boolean autheticated;
    private Date created;
    private Date expiration;
    private String accessToken;
    private String refreshToken;

}
