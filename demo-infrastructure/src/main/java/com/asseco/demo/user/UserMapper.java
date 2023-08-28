package com.asseco.demo.user;

import com.asseco.demo.usercontacts.UserContactMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final UserContactMapper userContactMapper;

    public User toUser(UserDbForm form) {
        var user = User.builder()
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .idNumber(form.getIdNumber())
                .build();

        var contacts = userContactMapper.toUserContacts(user, form.getContacts());
        user.setUserContacts(contacts);

        return user;
    }

    public Set<User> toUserSet(Set<UserDbForm> users) {
        return users.stream().map(this::toUser).collect(Collectors.toSet());
    }

}
