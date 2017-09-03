package com.odmytrenko.spring.controller;

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
    public ModelAndView findCategory(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("category", productService.findById(id));
        mv.setViewName("category");
        return mv;
    }
}
