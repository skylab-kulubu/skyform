package com.weblab.skyform.business.concretes;

import com.weblab.skyform.business.abstracts.EventService;
import com.weblab.skyform.business.abstracts.FormService;
import com.weblab.skyform.business.abstracts.UserService;
import com.weblab.skyform.business.constants.FormMessages;
import com.weblab.skyform.core.utilities.results.*;
import com.weblab.skyform.dataAccess.abstracts.FormDao;
import com.weblab.skyform.entities.Event;
import com.weblab.skyform.entities.Form;
import com.weblab.skyform.entities.dtos.form.CreateFormDto;
import com.weblab.skyform.entities.dtos.form.GetFormDto;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FormManager implements FormService {

    private final FormDao formDao;

    private final UserService userService;

    private final EventService eventService;


    public FormManager(EventService eventService, FormDao formDao, UserService userService) {
        this.eventService = eventService;
        this.formDao = formDao;
        this.userService = userService;
    }

    @Override
    public Result addForm(CreateFormDto createFormDto) {
        var userResult = userService.getLoggedInUser();
        if (!userResult.isSuccess()) {
            return userResult;
        }
        var user = userResult.getData();

        /*
        DEPRECATED, WONT BE USING EVENT FEATURE BECAUSE OF SKYEVENT - WILL INTEGRATE EVENT FEATURE FROM SKYEVENT
        Event event;

        if (createFormDto.getEventId() > 0) {
            var eventResult = eventService.getEventById(createFormDto.getEventId());

            if(!eventResult.isSuccess()) {
                return eventResult;
            }
            event = eventResult.getData();
        }else {
            event = null;
        }
         */
        Event event = null;

        var formToSave = Form.builder()
                .name(createFormDto.getName())
                .description(createFormDto.getDescription())
                .creationDate(new Date())
                .startDate(createFormDto.getStartDate())
                .endDate(createFormDto.getEndDate())
                .creator(user)
                .event(event)
                .build();

        formDao.save(formToSave);

        return new SuccessResult(FormMessages.formAddedSuccessfully);

    }

    @Override
    public DataResult<List<Form>> getAllForms() {
        var result = formDao.findAll();

        if(result.isEmpty()) {
            return new ErrorDataResult<>(FormMessages.formsNotFound);
        }

        return new SuccessDataResult<>(result, FormMessages.formsSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetFormDto>> getAllFormsDto() {
        var formList = formDao.findAll();

        if(formList.isEmpty()) {
            return new ErrorDataResult<>(FormMessages.formsNotFound);
        }

        List<GetFormDto> returnList = new GetFormDto().buildListGetFormDto(formList);

        return new SuccessDataResult<>(returnList, FormMessages.formsSuccessfullyBrought);

    }

    @Override
    public DataResult<Form> getFormById(int formId) {
        var result = formDao.findById(formId);

        if(!result.isPresent()) {
            return new ErrorDataResult<>(FormMessages.formNotFound);
        }


        return new SuccessDataResult<>(result.get(), FormMessages.formSuccessfullyBrought);
    }

    @Override
    public DataResult<GetFormDto> getFormDtoById(int formId) {
        var result = formDao.findById(formId);

        if(!result.isPresent()) {
            return new ErrorDataResult<>(FormMessages.formNotFound);
        }

        var returnForm = new GetFormDto(result.get());

        return new SuccessDataResult<>(returnForm, FormMessages.formSuccessfullyBrought);
    }
}

