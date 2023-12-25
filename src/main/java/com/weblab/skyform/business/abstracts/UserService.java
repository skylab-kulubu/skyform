package com.weblab.skyform.business.abstracts;

import com.weblab.skyform.core.utilities.result.DataResult;
import com.weblab.skyform.core.utilities.result.Result;
import com.weblab.skyform.entities.User;

import java.util.List;

public interface UserService {

    DataResult<User> getUserByUserId(int userId);

    Result addUser(User user);

    DataResult<List<User>> getUsers();

}
