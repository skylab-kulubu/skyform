package com.weblab.skyform.business.concretes;

import com.weblab.skyform.business.abstracts.EventService;
import com.weblab.skyform.business.abstracts.FormService;
import com.weblab.skyform.business.abstracts.UserService;
import com.weblab.skyform.business.constants.FormMessages;
import com.weblab.skyform.core.utilities.results.Result;
import com.weblab.skyform.core.utilities.results.SuccessResult;
import com.weblab.skyform.dataAccess.abstracts.FormDao;
import com.weblab.skyform.entities.Form;
import com.weblab.skyform.entities.dtos.FormDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormManager implements FormService {

    @Autowired
    private FormDao formDao;

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Override
    public Result add(FormDto formDto) {

        var formToAdd = Form.builder()
                .user(userService.getById(formDto.getFormCreatorId()).getData())
                .name(formDto.getName())
                .description(formDto.getDescription())
                .creationDate(formDto.getCreationDate())
                .startDate(formDto.getStartDate())
                .endDate(formDto.getEndDate())
                .event(eventService.getById(formDto.getEventId()).getData())
                .build();

        formDao.save(formToAdd);

        return new SuccessResult(FormMessages.formAddedSuccessfully);

    }
}
