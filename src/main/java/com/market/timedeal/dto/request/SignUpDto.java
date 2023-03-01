package com.market.timedeal.dto.request;

import com.market.timedeal.domain.Role;
import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Builder
public class SignUpDto {
    private String userId;
    private String name;
    private String password;
    private String address;
    @Enumerated(EnumType.STRING)
    private Role role;
}
