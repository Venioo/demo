package com.asseco.demo.usercontact;

import com.asseco.demo.common.UserContactCode;
import com.asseco.demo.exception.ErrorCode;
import com.asseco.demo.exception.MyException;
import com.asseco.demo.user.UserRepository;
import com.asseco.demo.usercontacts.form.UserContactCreateForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserContactValidator {

    private static final String DIGITS_REGEX = "\\d+";

    private final UserRepository userRepository;

    public void validateSave(Long userId, Set<UserContactCreateForm> forms) {
        validateUserExists(userId);
        validateContacts(forms);
    }

    private void validateContacts(Set<UserContactCreateForm> forms) {
        forms.forEach(form -> {
            var code = UserContactCode.valueOf(form.getCode());
            switch (code) {
                case EMAIL -> validateEmail(form);
                case PRIVATE_PHONE_NUMBER -> validatePhoneNumber(form);
                //TODO reszta caseów dla pozostałych wartości z enuma
            }
        });
    }

    private void validateEmail(UserContactCreateForm form) {
        //przykładowa prowizoryczna walidacja
        if (!form.getValue().contains("@gmail.com")) {
            throw new MyException(ErrorCode.DEMO_3.name(), "Invalid email: " + form.getValue(), HttpStatus.BAD_REQUEST);
        }
    }

    private void validatePhoneNumber(UserContactCreateForm form) {
        //przykładowa prowizoryczna walidacja
        if (form.getValue().matches(DIGITS_REGEX)) {
            throw new MyException(ErrorCode.DEMO_4.name(), "Invalid phone number: " + form.getValue(), HttpStatus.BAD_REQUEST);
        }
    }

    private void validateUserExists(Long userId) {
        var exists = userRepository.existsById(userId);
        if (!exists) {
            throw new MyException(ErrorCode.DEMO_2.name(), "User with given id does not exist: " + userId, HttpStatus.BAD_REQUEST);
        }
    }

}
