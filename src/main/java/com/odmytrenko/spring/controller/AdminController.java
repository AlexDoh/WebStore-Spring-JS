package com.odmytrenko.spring.controller;

import com.odmytrenko.spring.model.Category;
import com.odmytrenko.spring.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    @RequestMapping(value = "/adminconsole")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("category", new Category());
        mv.addObject("product", new Product());
        mv.setViewName("adminConsole");
        return mv;
    }
}
