package com.odmytrenko.spring.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerController {

    private static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public ModelAndView defaultErrorHandler(Exception e) {
        ModelAndView mv = new ModelAndView(DEFAULT_ERROR_VIEW);
        mv.addObject("error", e);
        mv.setViewName("error");
        return mv;
    }
}
