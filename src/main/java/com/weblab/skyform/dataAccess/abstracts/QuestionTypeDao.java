package com.weblab.skyform.dataAccess.abstracts;

import com.weblab.skyform.entities.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionTypeDao extends JpaRepository<QuestionType, Integer> {

    QuestionType getQuestionTypeById(int id);


}
