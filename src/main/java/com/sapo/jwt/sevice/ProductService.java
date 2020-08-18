package com.sapo.jwt.sevice;

import com.sapo.jwt.model.entity.Product;
import com.sapo.jwt.model.request.ProductRequest;

import java.util.List;

public interface ProductService {
    List<Product> findAll(int page, String name);

    Product create(ProductRequest productRequest);

    Product update(ProductRequest productRequest, long id, int status);

    Product findOne(long id);
}
