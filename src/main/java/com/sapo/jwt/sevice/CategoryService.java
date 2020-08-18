package com.sapo.jwt.sevice;

import com.sapo.jwt.model.entity.Category;
import com.sapo.jwt.model.request.CategoryRequest;

import java.util.List;

public interface CategoryService {
    List<Category> findAll(int page);

    Category create(CategoryRequest categoryRequest);

    Category update(CategoryRequest categoryRequest, long id, boolean status);

    Category findOne(long id);

    void deleteWithTransactionSpring(Long id);

    void deleteWithTransactionMySql(Long id);

    void deleteByUpdateStatus(Long id);
}
