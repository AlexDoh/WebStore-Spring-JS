package com.odmytrenko.spring.controller;

import com.odmytrenko.spring.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
public class ImageController {

    @Autowired
    ImageService imageService;

    @RequestMapping(value = "/profile/imageupload", method = RequestMethod.POST)
    public ModelAndView userImageUpload(@RequestParam CommonsMultipartFile image, @RequestParam String userName) {
        imageService.imageUpload(image, userName);
        ModelAndView mv = new ModelAndView();
        mv.addObject("name", userName);
        mv.addObject("type", "Image for");
        mv.setViewName("performedAction");
        return mv;
    }

    @RequestMapping(value = "/userimages")
    public void userImageLoad(HttpServletRequest request, HttpServletResponse response, @RequestParam String username) {
        try {
            File file = imageService.imageLoad(username);
            response.setHeader("Content-Type", request.getServletContext().getMimeType(file.getName()));
            response.setHeader("Content-Length", String.valueOf(file.length()));
            response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
            Files.copy(file.toPath(), response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException("Such user image doesn't exist " + e);
        }
    }
}
