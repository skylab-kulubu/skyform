package com.weblab.skyform.business.concretes;

import com.weblab.skyform.business.abstracts.UserService;
import com.weblab.skyform.business.constants.UserMessages;
import com.weblab.skyform.core.utilities.results.*;
import com.weblab.skyform.dataAccess.abstracts.UserDao;
import com.weblab.skyform.entities.User;
import com.weblab.skyform.entities.dtos.user.CreateUserDto;
import com.weblab.skyform.entities.dtos.user.GetUserDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManager implements UserService {

    private final UserDao userDao;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserManager(BCryptPasswordEncoder passwordEncoder, UserDao userDao) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
    }

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

       if (CheckIfUserExistsByMail(createUserDto.getEmail())){
           return new ErrorResult(UserMessages.userAlreadyExistsByMail);
       }

        if (CheckIfUserExistsBySchoolNumber(createUserDto.getSchoolNumber())){
            return new ErrorResult(UserMessages.userAlreadyExistsBySchoolNumber);
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

    private boolean CheckIfUserExistsByMail(String email) {

        var user = userDao.findByEmail(email);

        if (user.isPresent()){
            return true;
        }
        return false;

    }

    private boolean CheckIfUserExistsBySchoolNumber(String schoolNumber) {

        var user = userDao.findBySchoolNumber(schoolNumber);

        if (user.isPresent()){
            return true;
        }
        return false;

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

        if(!result.isPresent()){
            return new ErrorDataResult<>(UserMessages.userDoesntExist);
        }

        return new SuccessDataResult<User>(result.get(), UserMessages.getByIdSuccess);
    }

    @Override
    public DataResult<GetUserDto> getUserDtoById(int id) {
        var user = userDao.findById(id);

     if(!user.isPresent()){
         return new ErrorDataResult<>(UserMessages.userDoesntExist);
     }

        var returnUser = new GetUserDto(user.get());

        return new SuccessDataResult<GetUserDto>(returnUser, UserMessages.getByIdSuccess);
    }

    @Override
    public DataResult<User> getUserByEmail(String email) {
        var result = userDao.findByEmail(email);

        if(!result.isPresent()){
            return new ErrorDataResult<>(UserMessages.userDoesntExist);
        }

        return new SuccessDataResult<User>(result.get(), UserMessages.getByEmailSuccess);
    }


    @Override
    public DataResult<GetUserDto> getUserDtoByEmail(String email) {
        var user = userDao.findByEmail(email);

        if(!user.isPresent()){
            return new ErrorDataResult<>(UserMessages.userDoesntExist);
        }

        var returnUser = new GetUserDto(user.get());

        return new SuccessDataResult<GetUserDto>(returnUser, UserMessages.getByEmailSuccess);
    }

    @Override
    public Result updateUser(User user) {
        var result = userDao.findById(user.getId());

        if(!result.isPresent()){
            return new ErrorDataResult<>(UserMessages.userDoesntExist);
        }

        var userToUpdate = result.get();

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

    @Override
    public DataResult<User> getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal()=="anonymousUser"){
            return new ErrorDataResult<>(UserMessages.userNotLoggedIn);
        }

        var userMail = authentication.getName();
        return getUserByEmail(userMail);
    }

    //using email instead of username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByEmail(username).get();
    }
}
