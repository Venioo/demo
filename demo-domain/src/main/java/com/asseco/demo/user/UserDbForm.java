package com.asseco.demo.user;

import com.asseco.demo.usercontact.UserContactDbForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDbForm {

    private String firstName;

    private String lastName;

    private long idNumber;

    private Set<UserContactDbForm> contacts;

}
