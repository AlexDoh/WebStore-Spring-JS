package com.odmytrenko.spring.controller;

import com.odmytrenko.spring.model.Category;
import com.odmytrenko.spring.model.Product;
import com.odmytrenko.spring.model.Roles;
import com.odmytrenko.spring.model.User;
import com.odmytrenko.spring.model.RolesClassWrapper;
import com.odmytrenko.spring.service.CategoryService;
import com.odmytrenko.spring.service.ImageService;
import com.odmytrenko.spring.service.ProductService;
import com.odmytrenko.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ImageService imageService;

    @RequestMapping(value = {"/adminconsole", "/adminconsole/deleteuser/", "/adminconsole/manageuser/",
            "/adminconsole/managecategory", "/adminconsole/manageproduct"})
    public ModelAndView admin() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("category", new Category());
        mv.addObject("product", new Product());
        mv.addObject("categories", categoryService.getAll());
        mv.addObject("products", productService.getAll());
        mv.setViewName("adminConsole");
        return mv;
    }

    @RequestMapping(value = "/adminconsole/usermanagement")
    public ModelAndView management() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", new User());
        mv.addObject("users", userService.getAll());
        mv.setViewName("userManagement");
        return mv;
    }

    @RequestMapping(value = "/adminconsole/deleteuser")
    public ModelAndView deleteUser(@RequestParam String userName) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("userName", userName);
        mv.setViewName("deleteUser");
        return mv;
    }

    @RequestMapping(value = "/adminconsole/deleteuser", method = RequestMethod.POST)
    public ModelAndView confirmDeleteUser(@RequestParam String userName) {
        User user = new User();
        user.setName(userName);
        userService.delete(user);
        ModelAndView mv = new ModelAndView();
        mv.addObject("name", userName);
        mv.addObject("type", "User");
        mv.setViewName("performedAction");
        return mv;
    }

    @RequestMapping(value = "/adminconsole/manageuser")
    public ModelAndView updateUser(@ModelAttribute("user") User user) {
        ModelAndView mv = new ModelAndView();
        User resultUser = userService.getByUsernameAndPassword(user.getName(), user.getPassword());
        mv.addObject("user", resultUser);
        mv.addObject("allRoles", Arrays.stream(Roles.values()).map(RolesClassWrapper::wrapRole)
                .collect(Collectors.toSet()));
        mv.setViewName("manageUser");
        return mv;
    }

    @RequestMapping(value = "/adminconsole/manageuser", method = RequestMethod.POST)
    public ModelAndView updateUserMenu(@ModelAttribute("user") User user, @RequestParam CommonsMultipartFile image) {
        userService.update(user);
        if (image.getSize() != 0) {
            imageService.imageUpload(image, user.getName());
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("name", user.getName());
        mv.addObject("type", "User");
        mv.setViewName("performedAction");
        return mv;
    }

    @RequestMapping(value = "/adminconsole/managecategory", method = RequestMethod.POST)
    public ModelAndView manageCategory(@ModelAttribute("category") Category category, @RequestParam String action) {
        switch (action) {
            case "add":
                categoryService.create(category);
                break;
            case "delete":
                categoryService.delete(category);
                break;
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("name", category.getName());
        mv.addObject("type", "Category");
        mv.setViewName("performedAction");
        return mv;
    }

    @RequestMapping(value = "/adminconsole/manageproduct", method = RequestMethod.POST)
    public ModelAndView manageProduct(@ModelAttribute("product") Product product, @RequestParam String action) {
        switch (action) {
            case "add":
                productService.create(product);
                break;
            case "delete":
                productService.delete(product);
                break;
            case "update":
                productService.update(product);
                break;
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("name", product.getName());
        mv.addObject("type", "Product");
        mv.setViewName("performedAction");
        return mv;
    }
}
