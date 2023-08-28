package com.asseco.demo.user;

import com.asseco.demo.user.form.UserCreateForm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UnitAssemblerTest {

    @InjectMocks
    private UserAssembler userAssembler;

    @Test
    void validateIdNumberTest() {
        var form = new UserCreateForm("firstName", "lastName", 12345678901L);
        var dbForm = userAssembler.toUserDbForm(form);

        Assertions.assertEquals(form.getIdNumber(), dbForm.getIdNumber());
        Assertions.assertEquals(form.getFirstName(), dbForm.getFirstName());
        Assertions.assertEquals(form.getLastName(), dbForm.getLastName());
        Assertions.assertNull(dbForm.getContacts());
    }

}
