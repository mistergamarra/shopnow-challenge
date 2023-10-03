package com.mistergamarra.shopnow.accountsvc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtDto {

    String accessToken;
    String refreshToken;



}
