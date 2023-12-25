package com.weblab.skyform.dataAccess.abstracts;

import com.weblab.skyform.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

     User findUserById(int id);

}
