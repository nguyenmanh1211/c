package com.sapo.jwt.sevice.Impl;

import com.sapo.jwt.dao.common.ObjectDAO;
import com.sapo.jwt.model.entity.Category;
import com.sapo.jwt.model.entity.Product;
import com.sapo.jwt.model.request.CategoryRequest;
import com.sapo.jwt.sevice.CategoryService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService
{

    ObjectDAO<Product> productDAO;
    ObjectDAO<Category> categoryDAO;
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private long categoryCodeMax;

    @Autowired
    public CategoryServiceImpl(ObjectDAO<Product> productDAO, ObjectDAO<Category> categoryDAO) {
        try {
            this.productDAO = productDAO;
            this.categoryDAO = categoryDAO;
        } finally {
            String sql = "select substring_index(category_code,'g',-1) as ctcode from category where category_code like '%ctg%' order by ctcode desc limit 0,1";
            categoryCodeMax = categoryDAO.getLong(sql);
        }
    }

    @Override
    public List<Category> findAll(int page) {
        String sql = "select * from category limit " + ((page - 1) * 10) + ",10";
        return categoryDAO.findAll(sql, Category.class);
    }

    @Override
    public Category create(CategoryRequest categoryRequest) {
        Category category = new Category();
        String categoryCode = "ctg" + (categoryCodeMax + 1);
        try {
            BeanUtils.copyProperties(categoryRequest, category);
            if (!StringUtils.isNotBlank(category.getCategoryCode()))
                category.setCategoryCode(categoryCode);
            category.setCreatedDate(new Date());
            categoryDAO.create(category);
            categoryCodeMax += 1;
        } catch (DuplicateKeyException e) {
            log.error(e.getCause().getMessage());
            throw new ResponseStatusException(HttpStatus.OK, "Đã có mã của kho này!!!");
        }
        return category;
    }

    @Override
    public Category update(CategoryRequest categoryRequest, long id, boolean status) {
        Category category = findOne(id);
        try {
            BeanUtils.copyProperties(categoryRequest, category);
            if (StringUtils.isNotBlank(category.getCategoryCode()) && status) {
                category.setCategoryCode(categoryRequest.getCategoryCode());
            }
            category.setModifiedDate(new Date());
            categoryDAO.update(category, id, status);
        } catch (DuplicateKeyException e) {
            log.error(e.getCause().getMessage());
            throw new ResponseStatusException(HttpStatus.OK, "Đã có mã của kho này!!!");
        }
        return category;
    }

    @Override
    public Category findOne(long id) {
        String sql = "select * from category where id = " + id;
        return categoryDAO.findOne(sql, Category.class);
    }

    @Override
    @Transactional
    public void deleteWithTransactionSpring(Long id) {
        productDAO.delete("delete from product where category_id =" + id);
        categoryDAO.delete("delete from category where id= " + id);
    }

    @Override
    public void deleteWithTransactionMySql(Long id) {

    }

    @Override
    public void deleteByUpdateStatus(Long id) {
        List<Product> products = productDAO.findAll("select * from product where category_id = " + id, Product.class);
        Category category = new Category();
        category.setStatus(false);
        int caCheck = categoryDAO.update(category, id, false);
        int proCheck = 0;
        if (products.size() != 0) {
            for (Product product : products) {
                product.setStatus(false);
                proCheck = productDAO.update(product, product.getId(), false);
            }
            if (caCheck == 1 && proCheck == 1) {
                log.info("Deleted category and " + products.size() + " product by id = " + id + " success");
                return;
            }
        }
        if (caCheck == 1) {
            log.info("Deleted category by id = " + id + " success");
            return;
        }
        throw new ResponseStatusException(HttpStatus.OK, "Can't delete category!!!");
    }
}
