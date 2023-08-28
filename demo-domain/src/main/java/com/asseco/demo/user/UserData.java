package com.asseco.demo.user;

import com.asseco.demo.usercontact.UserContactData;

import java.util.Set;

public interface UserData {

    Long getId();

    String getFirstName();

    String getLastName();

    long getIdNumber();

    Set<UserContactData> getUserContactsData();
}
