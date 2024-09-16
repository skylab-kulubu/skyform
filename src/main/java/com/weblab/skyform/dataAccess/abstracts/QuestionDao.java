package com.weblab.skyform.dataAccess.abstracts;

import com.weblab.skyform.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
    /*
    write a method for his description
     private boolean CheckIfFormContainsQuestionWithOrder(Form data, int questionOrder) {
        //writing this method in backend is a bad practice we should be doing this on database query
    }
     */
    @Query("SELECT q FROM Question q WHERE q.form.id = ?1 AND q.questionOrder = ?2")
    Optional<Question> findByFormIdAndQuestionOrder(int formId, int questionOrder);


    Optional<List<Question>> findAllByFormId(int formId);


}
