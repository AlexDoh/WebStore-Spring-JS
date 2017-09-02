package com.odmytrenko.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String homePage(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("logout") != null) {
            Arrays.stream(request.getCookies()).forEach(c -> {
                if (c.getName().equals("TOKEN")) {
                    c.setValue(null);
                    c.setMaxAge(0);
                    c.setPath("/");
                    response.addCookie(c);
                }
            });
        }
        return "home";
    }
}
