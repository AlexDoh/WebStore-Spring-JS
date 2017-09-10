package com.odmytrenko.spring.controller;

import com.odmytrenko.spring.model.Product;
import com.odmytrenko.spring.service.CategoryService;
import com.odmytrenko.spring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/products")
    public ModelAndView categories(@RequestParam Long categoryId) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("products", productService.getAllByCategoryId(categoryId));
        mv.addObject("category", categoryService.findById(categoryId));
        mv.setViewName("products");
        return mv;
    }

    @RequestMapping(value = "/product")
    public ModelAndView findProduct(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView();
        Product product = productService.findById(id);
        mv.addObject("product", productService.findById(id));
        mv.addObject("category", categoryService.findById(product.getCategory().getId()));
        mv.setViewName("product");
        return mv;
    }
}
