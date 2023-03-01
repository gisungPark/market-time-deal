package com.market.timedeal.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Builder
public class LoginDto {

    private String userId;
    private String password;
}
