package com.weblab.skyform.business.concretes;

import com.weblab.skyform.business.abstracts.UserService;
import com.weblab.skyform.business.constants.Messages;
import com.weblab.skyform.core.utilities.result.DataResult;
import com.weblab.skyform.core.utilities.result.Result;
import com.weblab.skyform.core.utilities.result.SuccessDataResult;
import com.weblab.skyform.core.utilities.result.SuccessResult;
import com.weblab.skyform.dataAccess.abstracts.UserDao;
import com.weblab.skyform.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManager implements UserService {

    private UserDao userDao;

    @Autowired
    public UserManager(UserDao userDao){
        this.userDao = userDao;
    }


    @Override
    public DataResult<User> getUserByUserId(int userId) {
        var result = userDao.findUserById(userId);
        return new SuccessDataResult<User>(result, Messages.userBroughtByIdSuccess);
    }

    @Override
    public Result addUser(User user) {
        userDao.save(user);
        return new SuccessResult(Messages.userAddSuccess);
    }

    @Override
    public DataResult<List<User>> getUsers() {
        var result = userDao.findAll();
        return new SuccessDataResult<List<User>>(result, Messages.usersBroughtSuccess);
    }
}
