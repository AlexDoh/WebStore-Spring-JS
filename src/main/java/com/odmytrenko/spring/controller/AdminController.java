package com.odmytrenko.spring.controller;

import com.odmytrenko.spring.model.Category;
import com.odmytrenko.spring.model.Product;
import com.odmytrenko.spring.model.Roles;
import com.odmytrenko.spring.model.User;
import com.odmytrenko.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/adminconsole")
    public ModelAndView admin() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("category", new Category());
        mv.addObject("product", new Product());
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
        user.setRoles(userService.getByUsernameAndPassword(user.getName(), user.getPassword()).getRoles());
        mv.addObject("user", user);
        mv.addObject("allRoles", Roles.values());
        mv.setViewName("manageUser");
        return mv;
    }

    @RequestMapping(value = "/adminconsole/manageuser", method = RequestMethod.POST)
    public ModelAndView updateUserMenu(@ModelAttribute("user") User user) {
        userService.update(user);
        ModelAndView mv = new ModelAndView();
        mv.addObject("name", user.getName());
        mv.addObject("type", "User");
        mv.setViewName("performedAction");
        return mv;
    }
}