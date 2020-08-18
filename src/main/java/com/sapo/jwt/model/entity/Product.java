package com.sapo.jwt.model.entity;

import com.sapo.jwt.anotation.Column;
import com.sapo.jwt.anotation.Entity;
import com.sapo.jwt.anotation.Table;
import lombok.*;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    private long id;
    @Column(name = "product_code")
    private String productCode;
    @NotNull(message = "xxxx")
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_description")
    private String productDescription;
    @Column(name = "picture_path")
    private String picturePath;
    @Column(name = "product_amount")
    private Long productAmount;
    @Column(name = "\u200Bamount_sold")
    private Long amountSold;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "modified_date")
    private Date modifiedDate;
    @Column(name = "prices")
    private BigDecimal prices;
    @Column(name = "category_id")
    private Long categoryId;
    @Column(name = "storage_id")
    private Long storageId;
    @Column(name = "status")
    private Boolean status;
}
