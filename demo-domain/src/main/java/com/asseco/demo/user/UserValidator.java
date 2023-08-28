package com.asseco.demo.user;

import com.asseco.demo.exception.ErrorCode;
import com.asseco.demo.exception.MyException;
import com.asseco.demo.user.form.UserCreateForm;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    public void validateSave(UserCreateForm form) {
        validateIdNumber(form.getIdNumber());
    }

    void validateIdNumber(long idNumber) {
        // prowizoryczna walidacja peselu
        if (String.valueOf(idNumber).length() != 11) {
            throw new MyException(ErrorCode.DEMO_1.name(), "Id number must contain 11 digits : " + idNumber, HttpStatus.BAD_REQUEST);
        }
    }
}
