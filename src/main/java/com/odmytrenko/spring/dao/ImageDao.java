package com.odmytrenko.spring.dao;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;

public interface ImageDao {

    boolean imageUpload(CommonsMultipartFile file, String imageName);

    File imageLoad(String imageName);
}
