package com.weblab.skyform.dataAccess.abstracts;

import com.weblab.skyform.entities.Event;
import com.weblab.skyform.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventDao extends JpaRepository<Event, Integer> {

    Event findEventById(int id);

}
