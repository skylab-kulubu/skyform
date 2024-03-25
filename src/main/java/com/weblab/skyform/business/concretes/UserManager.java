package com.weblab.skyform.business.concretes;

import com.weblab.skyform.business.abstracts.UserService;
import com.weblab.skyform.business.constants.UserMessages;
import com.weblab.skyform.core.utilities.results.DataResult;
import com.weblab.skyform.core.utilities.results.SuccessDataResult;
import com.weblab.skyform.dataAccess.abstracts.UserDao;
import com.weblab.skyform.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManager implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public DataResult<User> getById(int id) {
        var result = userDao.findById(id);

        if (result == null)
            return new SuccessDataResult<User>(null, UserMessages.getByIdFail);

        return new SuccessDataResult<User>(result, UserMessages.getByIdSuccess);
    }
}
