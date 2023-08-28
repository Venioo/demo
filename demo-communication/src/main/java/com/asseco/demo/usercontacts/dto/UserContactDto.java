package com.asseco.demo.usercontacts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserContactDto {

    private Long id;

    private String code;

    private String value;

}
