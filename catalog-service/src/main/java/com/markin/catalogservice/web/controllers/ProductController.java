package com.markin.catalogservice.web.controllers;

import com.markin.catalogservice.domain.PagedResult;
import com.markin.catalogservice.domain.Product;
import com.markin.catalogservice.domain.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
class ProductController {

    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    PagedResult<Product> getProducts(@RequestParam(name = "page", defaultValue = "1") int pageNo){
        return productService.getProducts(pageNo);
    }
}
