package com.asseco.demo.usercontact;

import com.asseco.demo.usercontacts.form.UserContactCreateForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserContactService {

    private final UserContactValidator userContactValidator;

    private final UserContactAssembler userContactAssembler;

    private final UserContactRepository userContactRepository;

    public void addUserContacts(Long userId, Set<UserContactCreateForm> forms) {
        userContactValidator.validateSave(userId, forms);
        var dbForms = userContactAssembler.toUserContactDbForms(forms);
        userContactRepository.saveUserContacts(userId, dbForms);
    }

}
