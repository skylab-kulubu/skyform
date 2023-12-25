package com.weblab.skyform.dataAccess.abstracts;

import com.weblab.skyform.entities.QuestionOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionOptionDao extends JpaRepository<QuestionOption, Integer> {
}
