package com.asseco.demo.usercontacts.form;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class UserContactCreateForm {

    @NotEmpty
    @Size(max = 21)
    private String code;

    @NotEmpty
    @Size(max = 50)
    private String value;

}
