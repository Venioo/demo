package com.asseco.demo.usercontacts;

import com.asseco.demo.usercontact.UserContactService;
import com.asseco.demo.usercontacts.form.UserContactCreateForm;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Set;

import static com.asseco.demo.usercontacts.UserContactUris.ADD_USER_CONTACT;

@RestController
@RequiredArgsConstructor
public class UserContactsController {

    private final UserContactService userContactService;

    @PostMapping(ADD_USER_CONTACT)
    @Operation(summary = "Adds user's contacts")
    public void addUserContacts(@PathVariable Long userId, @RequestBody Set<@Valid UserContactCreateForm> forms) {
        userContactService.addUserContacts(userId, forms);
    }

}
