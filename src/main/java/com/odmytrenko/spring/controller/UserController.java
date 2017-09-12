package com.odmytrenko.spring.controller;

import com.odmytrenko.spring.model.User;
import com.odmytrenko.spring.service.ImageService;
import com.odmytrenko.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

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
    public ModelAndView profile(@ModelAttribute("user") User user, HttpServletRequest request,
                                HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        User userInput = userService.getByUsernameAndPassword(user.getName(), user.getPassword());
        if (user.getPassword().equals(userInput.getPassword())) {
            if (Boolean.parseBoolean(request.getParameter("token"))) {
                response.addCookie(new Cookie("TOKEN", userInput.getToken()));
            }
            mv.setViewName("profile");
            mv.addObject("message", "Login successful!");
        }
        return mv;
    }

    @RequestMapping(value = "/registration")
    public ModelAndView registration() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", new User());
        mv.setViewName("registration1");
        return mv;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registration(@ModelAttribute("user") User user, @RequestParam CommonsMultipartFile image) {
        ModelAndView mv = new ModelAndView();
        user.setToken(user.getName() + System.nanoTime());
        userService.create(user);
        if (image.getSize() != 0) {
            imageService.imageUpload(image, user.getName());
        }
        mv.addObject("name", user.getName());
        mv.addObject("type", "User");
        mv.setViewName("performedAction");
        return mv;
    }
}
