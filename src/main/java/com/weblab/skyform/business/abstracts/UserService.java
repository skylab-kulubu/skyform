package com.weblab.skyform.business.abstracts;

import com.weblab.skyform.core.utilities.results.DataResult;
import com.weblab.skyform.core.utilities.results.Result;
import com.weblab.skyform.entities.User;
import com.weblab.skyform.entities.dtos.user.CreateUserDto;
import com.weblab.skyform.entities.dtos.user.GetUserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    Result addUser(CreateUserDto createUserDto);

    DataResult<List<User>> getAllUsers();

    DataResult<List<GetUserDto>> getAllUsersDto();

    DataResult<User> getUserById(int id);

    DataResult<GetUserDto> getUserDtoById(int id);

    DataResult<User> getUserByEmail(String email);

    DataResult<GetUserDto> getUserDtoByEmail(String email);

    Result updateUser(User user);


}
