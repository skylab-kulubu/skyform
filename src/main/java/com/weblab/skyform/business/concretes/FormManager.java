package com.weblab.skyform.business.concretes;

import com.weblab.skyform.business.abstracts.EventService;
import com.weblab.skyform.business.abstracts.FormService;
import com.weblab.skyform.business.abstracts.UserService;
import com.weblab.skyform.business.constants.FormMessages;
import com.weblab.skyform.core.utilities.results.*;
import com.weblab.skyform.dataAccess.abstracts.FormDao;
import com.weblab.skyform.entities.Form;
import com.weblab.skyform.entities.User;
import com.weblab.skyform.entities.dtos.form.CreateFormDto;
import com.weblab.skyform.entities.dtos.form.GetFormDto;
import com.weblab.skyform.entities.dtos.user.GetUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FormManager implements FormService {

    @Autowired
    private FormDao formDao;

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Override
    public Result addForm(CreateFormDto createFormDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserMail = authentication.getName();

        var userResult = userService.getUserByEmail(currentUserMail);

        if(!userResult.isSuccess()){
            return userResult;
        }

        var user = userResult.getData();

        var formToSave = Form.builder()
                .name(createFormDto.getName())
                .description(createFormDto.getDescription())
                .creationDate(createFormDto.getCreationDate())
                .startDate(createFormDto.getStartDate())
                .endDate(createFormDto.getEndDate())
                .creator(user)
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

        List<GetFormDto> returnList = new ArrayList<>();

        formList.forEach(form -> {
            var returnForm = new GetFormDto().buildGetFormDto(form);
            returnList.add(returnForm);
        });

        return new SuccessDataResult<>(returnList, FormMessages.formsSuccessfullyBrought);

    }

    @Override
    public DataResult<Form> getFormById(int formId) {
        var result = formDao.findById(formId);

        if(result == null) {
            return new ErrorDataResult<>(FormMessages.formNotFound);
        }


        return new SuccessDataResult<>(result, FormMessages.formSuccessfullyBrought);
    }

    @Override
    public DataResult<GetFormDto> getFormDtoById(int formId) {
        var result = formDao.findById(formId);

        if(result == null) {
            return new ErrorDataResult<>(FormMessages.formNotFound);
        }

        var returnForm = new GetFormDto().buildGetFormDto(result);

        return new SuccessDataResult<>(returnForm, FormMessages.formSuccessfullyBrought);
    }
}

