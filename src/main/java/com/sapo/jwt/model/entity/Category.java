package com.sapo.jwt.model.entity;

import com.sapo.jwt.anotation.Column;
import com.sapo.jwt.anotation.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@Table(name = "category")
public class Category {
    @Column(name = "id")
    private long id;
    @Column(name = "category_code")
    private String categoryCode;
    @Column(name = "category_name")
    private String categoryName;
    @Column(name = "category_description")
    private String categoryDescription;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "modified_date")
    private Date modifiedDate;
    @Column(name = "status")
    private Boolean status;

}
