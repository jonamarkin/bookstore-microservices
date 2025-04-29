package com.markin.bookstorewebapp.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    @GetMapping
    String index(){
        return "redirect:/products";
    }

    @GetMapping("/products")
    String productsPage(){
        return "products";
    }
}
