package com.weblab.skyform.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "date_responses")
public class ResponseDate extends Response{

    private Date date;

    public ResponseDate() {
        setResponseType(QuestionType.TYPE_DATE);
    }

    public ResponseDate(Date date) {
        this();
        this.date = date;
    }

}
