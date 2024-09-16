package com.weblab.skyform.dataAccess.abstracts;

import com.weblab.skyform.entities.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FormDao extends JpaRepository<Form,Integer> {


}
