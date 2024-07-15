package com.weblab.skyform.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "text_responses")
public class ResponseText extends Response{

        private String text;

        public ResponseText() {
            setResponseType(QuestionType.TYPE_TEXT);
        }

        public ResponseText(String text) {
            this();
            this.text = text;
        }
}
