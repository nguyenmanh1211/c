package com.sapo.jwt.controller;

import com.sapo.jwt.model.entity.Product;
import com.sapo.jwt.model.request.ProductRequest;
import com.sapo.jwt.sevice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductApi {
    @Autowired
    private ProductService productService;

    @GetMapping(value = "/products")
    public List<Product> getAllProducts(@RequestParam String name,
                                        @Min(value = 1, message = "Page must be > 0!!!") @RequestParam int page) {
        return productService.findAll(page, name);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public Product getOneProducts(@PathVariable Long id) {
        return productService.findOne(id);
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public Product createProduct(@Validated @RequestBody ProductRequest productRequest) {
        return productService.create(productRequest);
    }

    @RequestMapping(value = "/products/{id}/{status}", method = RequestMethod.PUT)
    public Product updateProduct(@RequestBody @Valid ProductRequest productRequest, @PathVariable long id, @PathVariable int status) throws NoSuchFieldException, IllegalAccessException {
        return productService.update(productRequest, id, status);
    }

}
