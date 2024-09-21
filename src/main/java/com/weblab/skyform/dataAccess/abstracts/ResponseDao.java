package com.weblab.skyform.dataAccess.abstracts;

import com.weblab.skyform.entities.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResponseDao extends JpaRepository<Response, Integer> {

    Optional<List<Response>> findAllByResponseSession(String responseSession);

    Optional<List<Response>> findAllByQuestion_FormId(int formId);

}
