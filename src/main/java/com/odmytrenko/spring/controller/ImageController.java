package com.odmytrenko.spring.controller;

import com.odmytrenko.spring.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

@Controller
public class ImageController {

    @Value("${imageStore}")
    String filePath;

    @RequestMapping(value = "/profile/imageupload", method = RequestMethod.POST)
    public ModelAndView userImageUpload(@RequestParam CommonsMultipartFile image, @RequestParam String userName) {
        try {
            byte barr[] = image.getBytes();

            BufferedOutputStream bout = new BufferedOutputStream(
                    new FileOutputStream(filePath + File.separator + userName + ".jpg"));
            bout.write(barr);
            bout.flush();
            bout.close();

        } catch (Exception e) {
            throw new RuntimeException("There was problem with uploading file " + e);
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("performedAction");
        mv.addObject("name", userName);
        mv.addObject("type", "Image for");
        return mv;
    }

    @RequestMapping(value = "/userimages")
    public void userImageLoad(HttpServletRequest request, HttpServletResponse response, @RequestParam String username) {
        try {
            File file = new File(filePath, username + ".jpg");
            response.setHeader("Content-Type", request.getServletContext().getMimeType(file.getName()));
            response.setHeader("Content-Length", String.valueOf(file.length()));
            response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
            Files.copy(file.toPath(), response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException("Such user image doesn't exist " + e);
        }
    }
}
