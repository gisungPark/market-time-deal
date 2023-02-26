package com.market.timedeal.domain.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginDto {

    private String userId;
    private String password;
}
