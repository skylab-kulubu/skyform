package com.weblab.skyform.dataAccess.abstracts;

import com.weblab.skyform.entities.DateQuestionResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DateQuestionResponseDao extends JpaRepository<DateQuestionResponse, Integer> {

    DateQuestionResponse findById(int id);


}

