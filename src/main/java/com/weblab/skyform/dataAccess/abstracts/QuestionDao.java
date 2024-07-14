package com.weblab.skyform.dataAccess.abstracts;

import com.weblab.skyform.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

    Question findById(int id);

}
