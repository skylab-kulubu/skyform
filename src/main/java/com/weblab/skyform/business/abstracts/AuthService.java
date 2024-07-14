package com.weblab.skyform.business.abstracts;

import com.weblab.skyform.core.utilities.results.DataResult;
import com.weblab.skyform.core.utilities.results.Result;
import com.weblab.skyform.entities.dtos.user.CreateUserDto;
import com.weblab.skyform.entities.dtos.user.LoginUserDto;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {

    Result register(CreateUserDto createUserDto);

    DataResult<String> login(LoginUserDto loginUserDto, HttpServletResponse response);
}
