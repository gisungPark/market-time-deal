package com.market.timedeal.domain.user.dto;

import com.market.timedeal.domain.user.domain.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpDto {
    private String userId;
    private String name;
    private String password;
    private String address;
    private Role role;
}
