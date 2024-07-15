package com.weblab.skyform.business.concretes;

import com.weblab.skyform.business.abstracts.UserService;
import com.weblab.skyform.business.constants.UserMessages;
import com.weblab.skyform.core.utilities.results.*;
import com.weblab.skyform.dataAccess.abstracts.UserDao;
import com.weblab.skyform.entities.User;
import com.weblab.skyform.entities.dtos.user.CreateUserDto;
import com.weblab.skyform.entities.dtos.user.GetUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManager implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Result addUser(CreateUserDto createUserDto) {

        if(createUserDto.getFirstName() == null){
            return new ErrorResult(UserMessages.firstNameCannotBeNull);
        }

        if(createUserDto.getLastName() == null){
            return new ErrorResult(UserMessages.lastNameCannotBeNull);
        }

        if(createUserDto.getEmail() == null){
            return new ErrorResult(UserMessages.emailCannotBeNull);
        }

       if(createUserDto.getSchoolNumber() == null){
            return new ErrorResult(UserMessages.schoolNumberCannotBeNull);
        }

        if(createUserDto.getPhoneNumber() == null){
            return new ErrorResult(UserMessages.phoneNumberCannotBeNull);
        }

        if(createUserDto.getPassword() == null){
            return new ErrorResult(UserMessages.passwordCannotBeNull);
        }

        var userToSave = createUserDto.buildUserWithoutPassword(createUserDto);
        userToSave.setPassword(passwordEncoder.encode(createUserDto.getPassword()));

        userDao.save(userToSave);

        return new SuccessResult(UserMessages.userAdded);

    }

    @Override
    public DataResult<List<User>> getAllUsers() {
        var userList = userDao.findAll();

        if(userList.isEmpty()){
            return new ErrorDataResult<>(UserMessages.usersNotFound);
        }

        return new SuccessDataResult<List<User>>(userList, UserMessages.getUsersSuccess);
    }

    @Override
    public DataResult<List<GetUserDto>> getAllUsersDto() {
        var userList = userDao.findAll();

        if(userList.isEmpty()){
            return new ErrorDataResult<>(UserMessages.usersNotFound);
        }

        List<GetUserDto> returnList = new GetUserDto().buildListGetUserDto(userList);

        return new SuccessDataResult<List<GetUserDto>>(returnList, UserMessages.getUsersSuccess);
    }

    @Override
    public DataResult<User> getUserById(int id) {
        var result = userDao.findById(id);

        if(result == null){
            return new ErrorDataResult<>(UserMessages.userDoesntExist);
        }

        return new SuccessDataResult<User>(result, UserMessages.getByIdSuccess);
    }

    @Override
    public DataResult<GetUserDto> getUserDtoById(int id) {
        var user = userDao.findById(id);

     if(user == null){
         return new ErrorDataResult<>(UserMessages.userDoesntExist);
     }

        var returnUser = new GetUserDto(user);

        return new SuccessDataResult<GetUserDto>(returnUser, UserMessages.getByIdSuccess);
    }

    @Override
    public DataResult<User> getUserByEmail(String email) {
        var result = userDao.findByEmail(email);

        if(result == null){
            return new ErrorDataResult<>(UserMessages.userDoesntExist);
        }

        return new SuccessDataResult<User>(result, UserMessages.getByEmailSuccess);
    }


    @Override
    public DataResult<GetUserDto> getUserDtoByEmail(String email) {
        var user = userDao.findByEmail(email);

        if(user == null){
            return new ErrorDataResult<>(UserMessages.userDoesntExist);
        }

        var returnUser = new GetUserDto(user);

        return new SuccessDataResult<GetUserDto>(returnUser, UserMessages.getByEmailSuccess);
    }

    @Override
    public Result updateUser(User user) {
        var userToUpdate = userDao.findById(user.getId());

        if(userToUpdate == null){
            return new ErrorDataResult<>(UserMessages.userDoesntExist);
        }

         userToUpdate.setFirstName(user.getFirstName().isEmpty() ? userToUpdate.getFirstName() : user.getFirstName());
         userToUpdate.setLastName(user.getLastName().isEmpty() ? userToUpdate.getLastName() : user.getLastName());
         userToUpdate.setEmail(user.getEmail().isEmpty() ? userToUpdate.getEmail() : user.getEmail());
         userToUpdate.setSchoolNumber(user.getSchoolNumber().isEmpty() ? userToUpdate.getSchoolNumber() : user.getSchoolNumber());
         userToUpdate.setPhoneNumber(user.getPhoneNumber().isEmpty() ? userToUpdate.getPhoneNumber() : user.getPhoneNumber());
         userToUpdate.setPassword(user.getPassword().isEmpty() ? userToUpdate.getPassword() : user.getPassword());
         userToUpdate.setAuthorities(user.getAuthorities().isEmpty() ? userToUpdate.getAuthorities() : user.getAuthorities());

         userDao.save(userToUpdate);

         return new SuccessResult(UserMessages.userUpdateSuccess);

    }

    //using email instead of username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByEmail(username);
    }
}
