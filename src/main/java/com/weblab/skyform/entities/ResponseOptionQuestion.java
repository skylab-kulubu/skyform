package com.weblab.skyform.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "option_question_responses")
public class ResponseOptionQuestion extends Response{

    @ManyToOne
    @JoinColumn(name = "chosen_option_id", referencedColumnName = "id")
    private QuestionOption chosenOption;

}
