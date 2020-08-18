package com.sapo.jwt.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class CategoryRequest {
    private String categoryCode;
    @NotBlank(message = "category name must be not null!!!")
    private String categoryName;
    private String categoryDescription;
}
