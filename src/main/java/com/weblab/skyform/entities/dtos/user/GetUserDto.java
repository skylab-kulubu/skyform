package com.weblab.skyform.entities.dtos.user;

import com.weblab.skyform.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetUserDto {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String schoolNumber;
    private String phoneNumber;
    private Set<Role> authorities;

}
