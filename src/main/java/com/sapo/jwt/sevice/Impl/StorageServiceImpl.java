package com.sapo.jwt.sevice.Impl;

import com.sapo.jwt.dao.common.ObjectDAO;
import com.sapo.jwt.model.entity.Product;
import com.sapo.jwt.model.entity.Storage;
import com.sapo.jwt.model.request.StorageRequest;
import com.sapo.jwt.repository.StorageRepository;
import com.sapo.jwt.sevice.StorageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
public class StorageServiceImpl implements StorageService {

    private StorageRepository storageRepository;
    private ObjectDAO<Product> objectDAO;
    private Long storageCodeMax;

    @Autowired
    public StorageServiceImpl(StorageRepository storageRepository, ObjectDAO<Product> objectDAO) {
        try {
            this.storageRepository = storageRepository;
            this.objectDAO = objectDAO;
        } finally {
            storageCodeMax = storageRepository.getCodeMax();
        }
    }

    @Override
    public List<Storage> findAll(String name) {
        return storageRepository.findAllCustom(name); // select * from storages
    }

    @Override
    public Storage findOne(Long id) {
        return storageRepository.findOneCustom(id);
    }

    @Override
    public Storage create(StorageRequest storageRequest) {
        Storage storage = new Storage();
        String storageCode = "kho" + (storageCodeMax + 1);
        BeanUtils.copyProperties(storageRequest, storage);
        if (!StringUtils.isNotBlank(storage.getStorageCode())) {
            storage.setStorageCode(storageCode);
            storageCodeMax += 1;
        }
        storage.setCreatedDate(new Date());
        try {
            storageRepository.save(storage);
        } catch (DuplicateKeyException e) {
            throw new ResponseStatusException(HttpStatus.OK, "Trung ma kho!!!");
        }
        return storage;
    }

    @Override
    public Storage update(StorageRequest storageRequest, Long id) {
        Storage storage = storageRepository.findOneCustom(id);
        try {
            BeanUtils.copyProperties(storageRequest, storage);
            storage.setModifiedDate(new Date());

            storageRepository.save(storage);
        } catch (DataIntegrityViolationException e) {
//            System.out.println(e.getClass().getCanonicalName());
            throw new ResponseStatusException(HttpStatus.OK, "Trung ma kho!!!");
        }
        return storage;
    }

    @Override
    public void delete(Long id) {
        Product product = new Product();
        Storage storage = storageRepository.findOneCustom(id);
        product.setStatus(false);
        storage.setStatus(false);
        int proCheck = objectDAO.update(product, id, false);
        storageRepository.save(storage);
        if (proCheck != 1) throw new ResponseStatusException(HttpStatus.OK, "Can't delete this storage!!!");
    }
}
