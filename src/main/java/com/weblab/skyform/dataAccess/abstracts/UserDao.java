package com.weblab.skyform.dataAccess.abstracts;

import com.weblab.skyform.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

     Optional<User> findByEmail(String email);

        Optional<User> findBySchoolNumber(String schoolNumber);

}
