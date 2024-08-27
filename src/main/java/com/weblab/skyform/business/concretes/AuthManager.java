package com.weblab.skyform.business.concretes;

import com.weblab.skyform.business.abstracts.AuthService;
import com.weblab.skyform.business.abstracts.UserService;
import com.weblab.skyform.business.constants.AuthMessages;
import com.weblab.skyform.core.security.JwtService;
import com.weblab.skyform.core.utilities.results.DataResult;
import com.weblab.skyform.core.utilities.results.ErrorDataResult;
import com.weblab.skyform.core.utilities.results.Result;
import com.weblab.skyform.core.utilities.results.SuccessDataResult;
import com.weblab.skyform.entities.dtos.user.CreateUserDto;
import com.weblab.skyform.entities.dtos.user.LoginUserDto;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthManager implements AuthService {

    @Autowired
    UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Override
    public Result register(CreateUserDto createUserDto) {

        //will implement email authentication
        return userService.addUser(createUserDto);
    }

    @Override
    public DataResult<String> login(LoginUserDto loginUserDto, HttpServletResponse response) {

        if(loginUserDto.getEmail() == null){
            return new ErrorDataResult<>(AuthMessages.emailCannotBeNull);
        }

        if(loginUserDto.getPassword() == null){
            return new ErrorDataResult<>(AuthMessages.passwordCannotBeNull);
        }


        var email = loginUserDto.getEmail();
        var password = loginUserDto.getPassword();

        var userResult = userService.getUserByEmail(email);

        if(!userResult.isSuccess()){
            return new ErrorDataResult<>(userResult.getMessage());
        }


        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
        if (authentication.isAuthenticated()) {
            var token = jwtService.generateToken(email);

            return new SuccessDataResult<>(token, AuthMessages.tokenGeneratedSuccessfully);
        }

        return new ErrorDataResult<>(AuthMessages.invalidUsernameOrPassword);
    }
}
