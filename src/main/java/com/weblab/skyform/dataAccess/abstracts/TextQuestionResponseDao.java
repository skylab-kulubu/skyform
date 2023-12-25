package com.weblab.skyform.dataAccess.abstracts;

import com.weblab.skyform.entities.TextQuestionResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextQuestionResponseDao extends JpaRepository<TextQuestionResponse, Integer> {
}
