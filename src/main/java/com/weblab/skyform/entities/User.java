package com.weblab.skyform.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "formCreator", fetch = FetchType.LAZY)
    private List<Form> forms;

    @OneToMany(mappedBy = "responder", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<OptionQuestionResponse> optionQuestionResponses;

    @OneToMany(mappedBy = "responder", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<DateQuestionResponse> dateQuestionResponses;

    @OneToMany(mappedBy = "responder", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TextQuestionResponse> textQuestionResponses;



}
