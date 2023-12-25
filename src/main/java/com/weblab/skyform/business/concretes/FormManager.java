package com.weblab.skyform.business.concretes;

import com.weblab.skyform.business.abstracts.EventService;
import com.weblab.skyform.business.abstracts.FormService;
import com.weblab.skyform.business.abstracts.UserService;
import com.weblab.skyform.business.constants.Messages;
import com.weblab.skyform.core.utilities.result.*;
import com.weblab.skyform.dataAccess.abstracts.FormDao;
import com.weblab.skyform.entities.Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FormManager implements FormService {

    private FormDao formDao;

    private EventService eventService;

    private UserService userService;


    @Autowired
    public FormManager(FormDao formDao, EventService eventService, UserService userService){
        this.formDao = formDao;
        this.eventService = eventService;
        this.userService = userService;
    }

    @Override
    public Result addForm(Form form) {
        formDao.save(form);
        return new SuccessResult(Messages.formAddSuccess);
    }

    private boolean CheckIfEventNull(Form form) {
        if(form.getEvent().getName() == null){
            return true;
        }
        return false;
    }

    private boolean CheckIfFormHasEventId(Form form) {

        if (form.getEvent().getId() == 0) {
            return false;
        }
        return true;

    }

    @Override
    public DataResult<List<Form>> getForms() {
        var result = formDao.findAll();
        return new SuccessDataResult<List<Form>>(result, Messages.formsGetSuccess);
    }

    @Override
    public Result addEventToForm(int eventId, int formId) {
        var form = formDao.findById(formId);

        if(!checkIfFormExist(form)){
            return new ErrorResult(Messages.eventDoesntExist);
        }

        form.setEvent(eventService.getEventByEventId(eventId).getData());
        formDao.save(form);
        return new SuccessResult(Messages.formAddToEventSuccess);
    }

    @Override
    public DataResult<Form> getFormById(int id) {
        var result = formDao.findById(id);
        return new SuccessDataResult<Form>(result, Messages.formGetsuccess);
    }


    @Override
    public Result deleteForm(int formId) {
        formDao.deleteById(formId);
        return new SuccessResult(Messages.formDeleteSuccess);
    }


    private boolean checkIfFormExist(Form form){
        if (form == null){
            return false;
        }
        return true;
    }
}
