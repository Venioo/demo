package com.asseco.demo.user.search;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@SuperBuilder
@NoArgsConstructor
public class BaseSearchCriteria {

    @Min(1)
    @NotNull
    private Integer pageSize;

    @Min(0)
    @NotNull
    private Integer pageNumber;

}
