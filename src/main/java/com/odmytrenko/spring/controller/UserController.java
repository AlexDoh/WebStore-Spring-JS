package com.odmytrenko.spring.controller;

import com.odmytrenko.spring.model.User;
import com.odmytrenko.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", new User());
        mv.setViewName("login");
        return mv;
    }


    @RequestMapping(value = "/profile")
    public ModelAndView profile() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", new User());
        mv.setViewName("login");
        return mv;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public ModelAndView profile(@ModelAttribute("user") User user, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        User userInput = userService.getByUsername(user.getName(), user.getPassword());
        if (user.getPassword().equals(userInput.getPassword())) {
            response.addCookie(new Cookie("TOKEN", userInput.getToken()));
            mv.setViewName("profile");
            mv.addObject("message", "Login successful!");
        }
        return mv;
    }
}
