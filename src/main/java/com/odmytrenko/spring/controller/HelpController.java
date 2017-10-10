package com.odmytrenko.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Controller
public class HelpController {

    @RequestMapping("/help")
    public String helpPage() {
        return "help";
    }
}
