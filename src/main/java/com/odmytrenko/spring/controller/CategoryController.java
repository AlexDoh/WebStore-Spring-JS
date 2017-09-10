package com.odmytrenko.spring.controller;

import com.odmytrenko.spring.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/categories")
    public ModelAndView categories() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("categories", categoryService.getAll());
        mv.setViewName("categories");
        return mv;
    }

    @RequestMapping(value = "/category")
    public ModelAndView findCategory(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("category", categoryService.findById(id));
        mv.addObject("categories", categoryService.getAll());
        mv.setViewName("category");
        return mv;
    }
}
