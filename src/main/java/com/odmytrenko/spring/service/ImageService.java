package com.odmytrenko.spring.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;

public interface ImageService {

    boolean imageUpload(CommonsMultipartFile file, String imageName);

    File imageLoad(String imageName);

}
