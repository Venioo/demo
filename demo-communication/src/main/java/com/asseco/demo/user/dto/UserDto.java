package com.asseco.demo.user.dto;

import com.asseco.demo.usercontacts.dto.UserContactDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private long idNumber;

    private Set<UserContactDto> contacts;

}
