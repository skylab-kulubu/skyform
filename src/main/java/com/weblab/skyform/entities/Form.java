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
    private int id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private User creator;

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

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    @JsonIgnore
    private Event event;

    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL)
    private List<Question> questions;




}
