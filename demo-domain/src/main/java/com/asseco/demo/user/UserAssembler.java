package com.asseco.demo.user;

import com.asseco.demo.user.dto.UserDto;
import com.asseco.demo.user.form.UserCreateForm;
import com.asseco.demo.usercontact.UserContactAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserAssembler {

    private final UserContactAssembler userContactAssembler;

    public List<UserDto> toUserDtoList(List<UserData> users) {
        return users.stream().map(this::toUserDto).collect(Collectors.toList());
    }

    private UserDto toUserDto(UserData userData) {
        return UserDto.builder()
                .id(userData.getId())
                .firstName(userData.getFirstName())
                .lastName(userData.getLastName())
                .idNumber(userData.getIdNumber())
                .contacts(userContactAssembler.toUserContactDtoList(userData.getUserContactsData()))
                .build();
    }

    public UserDbForm toUserDbForm(UserCreateForm form) {
        return UserDbForm.builder()
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .idNumber(form.getIdNumber())
                .build();
    }

}
