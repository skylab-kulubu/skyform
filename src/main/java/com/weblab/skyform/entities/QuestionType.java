package com.weblab.skyform.entities;

public enum QuestionType {

    TYPE_TEXT("TEXT"),
    TYPE_DATE("DATE"),
    TYPE_RATING("RATING");


    private String value;

    QuestionType(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }


}
