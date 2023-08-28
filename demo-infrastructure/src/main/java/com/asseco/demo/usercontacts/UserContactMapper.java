package com.asseco.demo.usercontacts;

import com.asseco.demo.common.UserContactCode;
import com.asseco.demo.user.User;
import com.asseco.demo.usercontact.UserContactDbForm;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserContactMapper {

    public Set<UserContact> toUserContacts(User user, Set<UserContactDbForm> forms) {
        return forms.stream().map(form -> toUserContact(user, form)).collect(Collectors.toSet());
    }

    private UserContact toUserContact(User user, UserContactDbForm form) {
        return UserContact.builder()
                .user(user)
                .code(UserContactCode.valueOf(form.getCode()))
                .value(form.getValue())
                .build();
    }

}
