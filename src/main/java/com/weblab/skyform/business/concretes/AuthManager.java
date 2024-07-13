package com.weblab.skyform.business.concretes;

import com.weblab.skyform.business.abstracts.AuthService;
import com.weblab.skyform.business.abstracts.UserService;
import com.weblab.skyform.business.constants.AuthMessages;
import com.weblab.skyform.core.security.JwtService;
import com.weblab.skyform.core.utilities.results.DataResult;
import com.weblab.skyform.core.utilities.results.Result;
import com.weblab.skyform.core.utilities.results.SuccessDataResult;
import com.weblab.skyform.entities.dtos.user.CreateUserDto;
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
        return userService.addUser(createUserDto);
    }

    @Override
    public DataResult<String> login(String email, String password, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        if (authentication.isAuthenticated()) {
            var token = jwtService.generateToken(email);

            return new SuccessDataResult<>(token, AuthMessages.tokenGeneratedSuccessfully);
        }

        return new SuccessDataResult<>(AuthMessages.invalidUsernameOrPassword);
    }
}
