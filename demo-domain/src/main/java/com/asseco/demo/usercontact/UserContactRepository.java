package com.asseco.demo.usercontact;

import java.util.Set;

public interface UserContactRepository {

    void saveUserContacts(Long userId, Set<UserContactDbForm> forms);
}
