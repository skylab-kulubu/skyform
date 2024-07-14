package com.weblab.skyform.entities.dtos.user;

import com.weblab.skyform.entities.Role;
import com.weblab.skyform.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
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

    public GetUserDto buildGetUserDto(User user){
        return GetUserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .schoolNumber(user.getSchoolNumber())
                .phoneNumber(user.getPhoneNumber())
                .authorities(user.getAuthorities())
                .build();
    }

    public List<GetUserDto> buildListGetUserDto(List<User> users){
        List<GetUserDto> listGetUserDto = new ArrayList<>();
        for (User user : users) {
            listGetUserDto.add(buildGetUserDto(user));
        }
        return listGetUserDto;
    }





}
