package com.sapo.jwt.sevice;

import com.sapo.jwt.model.entity.Storage;
import com.sapo.jwt.model.request.StorageRequest;

import java.util.List;

public interface StorageService {
    List<Storage> findAll(String name);

    Storage findOne(Long id);

    Storage create(StorageRequest storageRequest);

    Storage update(StorageRequest storageRequest, Long id);

    void delete(Long id);
}
