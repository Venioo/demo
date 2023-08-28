package com.asseco.demo.usercontact;

import com.asseco.demo.usercontacts.dto.UserContactDto;
import com.asseco.demo.usercontacts.form.UserContactCreateForm;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserContactAssembler {

    public Set<UserContactDto> toUserContactDtoList(Set<UserContactData> userContacts) {
        return userContacts.stream().map(this::toUserContactDto).collect(Collectors.toSet());
    }

    private UserContactDto toUserContactDto(UserContactData userContactData) {
        return UserContactDto.builder()
                .id(userContactData.getId())
                .code(userContactData.getCode().name())
                .value(userContactData.getValue())
                .build();
    }

    public Set<UserContactDbForm> toUserContactDbForms(Set<UserContactCreateForm> userContacts) {
        return userContacts.stream().map(this::toUserContactDbForm).collect(Collectors.toSet());
    }

    private UserContactDbForm toUserContactDbForm(UserContactCreateForm form) {
        return UserContactDbForm.builder()
                .code(form.getCode())
                .value(form.getValue())
                .build();
    }

}
