package com.weblab.skyform.entities.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserDto {

    private String firstName;

    private String lastName;

    private String email;

    private String schoolNumber;

    private String phoneNumber;

    private String password;

}
