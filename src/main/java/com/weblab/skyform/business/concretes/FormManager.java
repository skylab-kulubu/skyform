package com.weblab.skyform.business.concretes;

import com.weblab.skyform.business.abstracts.EventService;
import com.weblab.skyform.business.abstracts.FormService;
import com.weblab.skyform.business.abstracts.UserService;
import com.weblab.skyform.business.constants.FormMessages;
import com.weblab.skyform.core.utilities.results.Result;
import com.weblab.skyform.core.utilities.results.SuccessResult;
import com.weblab.skyform.dataAccess.abstracts.FormDao;
import com.weblab.skyform.entities.Form;
import com.weblab.skyform.entities.dtos.CreateFormDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class FormManager implements FormService {

    @Autowired
    private FormDao formDao;

    @Autowired
    private UserService userService;

    @Override
    public Result addForm(CreateFormDto createFormDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();

        Form form = Form.builder()
                .name(createFormDto.getName())
                .description(createFormDto.getDescription())
                .creationDate(createFormDto.getCreationDate())
                .startDate(createFormDto.getStartDate())
                .endDate(createFormDto.getEndDate())
                //.event(eventService.getById(createFormDto.getEventId()).getData())
                .user(userService.getUserById(Integer.parseInt(userId)).getData())
                .build();




    }
}
