package com.weblab.skyform.dataAccess.abstracts;

import com.weblab.skyform.entities.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormDao extends JpaRepository<Form,Integer> {


}
