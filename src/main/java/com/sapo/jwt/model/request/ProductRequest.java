package com.sapo.jwt.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


public class ProductRequest {
    private String productCode;
    @NotBlank(message = "product name must be not null!!!")
    private String productName;
    private String productDescription;
    private String picturePath;
    @NotNull(message = "amount must be not null!!!")
//    @IsNumber(message = "amount must be number!!!")
    private Long productAmount;
//    @IsNumber(message = "amount sold must be number!!!")
    private Long amountSold;
    @NotNull(message = "prices must be not null!!!")
//    @IsNumber(message = "prices must be number!!!")
    private BigDecimal prices;
    @NotNull(message = "category must be not null!!!")
//    @IsNumber(message = "category id must be number!!!")
    private Long categoryId;
    @NotNull(message = "storage must be not null!!!")
//    @IsNumber(message = "storage id must be number!!!")
    private Long storageId;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public Long getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Long productAmount) {
        this.productAmount = productAmount;
    }

    public Long getAmountSold() {
        return amountSold;
    }

    public void setAmountSold(Long amountSold) {
        this.amountSold = amountSold;
    }

    public BigDecimal getPrices() {
        return prices;
    }

    public void setPrices(BigDecimal prices) {
        this.prices = prices;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long storageId) {
        this.storageId = storageId;
    }
}
