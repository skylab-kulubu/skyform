package com.weblab.skyform.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "forms")
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "form_creator_id")
    private User formCreator;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id")
    private Event event;

    @Column(name ="name")
    private String name;

    @Column(name ="description")
    private String description;

    @Column(name ="creation_date")
    private Date creationDate;

    @Column(name ="start_date")
    private Date startDate;

    @Column(name ="end_date")
    private Date endDate;

    @OneToMany(mappedBy = "form", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Question> questions;


}
