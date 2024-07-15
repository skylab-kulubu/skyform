package com.weblab.skyform.dataAccess.abstracts;

import com.weblab.skyform.entities.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseDao extends JpaRepository<Response, Integer> {

    Response findById(int id);
}
