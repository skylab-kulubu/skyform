package com.weblab.skyform.dataAccess.abstracts;

import com.weblab.skyform.entities.QuestionOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionOptionDao extends JpaRepository<QuestionOption, Integer> {

    QuestionOption findById(int id);

    List<QuestionOption> findAllByQuestionId(int questionId);


}
