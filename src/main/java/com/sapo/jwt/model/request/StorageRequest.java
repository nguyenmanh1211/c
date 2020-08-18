package com.sapo.jwt.model.request;

import javax.validation.constraints.NotBlank;

public class StorageRequest {
    private String storageCode;
    @NotBlank(message = "storage name must be not null!!!")
    private String storageName;
    private String storageAddress;
    private Boolean status;

    public String getStorageCode() {
        return storageCode;
    }

    public void setStorageCode(String storageCode) {
        this.storageCode = storageCode;
    }

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getStorageAddress() {
        return storageAddress;
    }

    public void setStorageAddress(String storageAddress) {
        this.storageAddress = storageAddress;
    }
}
