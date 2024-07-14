package com.weblab.skyform.entities.dtos.user;

import com.weblab.skyform.entities.Role;
import com.weblab.skyform.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

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

    public User buildUserWithoutPassword(CreateUserDto createUserDto){
        return User.builder()
                .firstName(createUserDto.getFirstName())
                .lastName(createUserDto.getLastName())
                .email(createUserDto.getEmail())
                .authorities(Set.of(Role.ROLE_USER))
                .schoolNumber(createUserDto.getSchoolNumber())
                .phoneNumber(createUserDto.getPhoneNumber())
                .accountNonExpired(true)
                .accountNonLocked(true)
                .isEnabled(true)
                .credentialsNonExpired(true)
                .build();
    }

}
