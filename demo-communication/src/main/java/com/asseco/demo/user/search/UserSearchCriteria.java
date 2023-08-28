package com.asseco.demo.user.search;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@SuperBuilder
@NoArgsConstructor
public class UserSearchCriteria extends BaseSearchCriteria {

    private Set<Long> ids;

    private Set<String> firstNames;

    private Set<String> lastNames;

    private Set<Long> idNumbers;

    private Set<String> contactCodes;

    private Set<String> contactValues;

}
