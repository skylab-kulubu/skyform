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
public class GetUserDto {

    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String schoolNumber;

    private String phoneNumber;

    private Set<Role> authorities;

    public GetUserDto(User user){
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.schoolNumber = user.getSchoolNumber();
        this.phoneNumber = user.getPhoneNumber();
        this.authorities = user.getAuthorities();
    }

    public List<GetUserDto> buildListGetUserDto(List<User> users){
        List<GetUserDto> listGetUserDto = new ArrayList<>();
        for (User user : users) {
            listGetUserDto.add(new GetUserDto(user));
        }
        return listGetUserDto;
    }





}
