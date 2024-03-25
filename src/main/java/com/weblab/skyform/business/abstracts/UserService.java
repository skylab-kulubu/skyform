package com.weblab.skyform.business.abstracts;

import com.weblab.skyform.core.utilities.results.DataResult;
import com.weblab.skyform.entities.User;

public interface UserService {

    DataResult<User> getById(int id);
}
