package com.odmytrenko.spring.service;

import com.odmytrenko.spring.dao.ImageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageDao imageDao;

    @Override
    public boolean imageUpload(CommonsMultipartFile file, String imageName) {
        return imageDao.imageUpload(file, imageName);
    }

    @Override
    public File imageLoad(String imageName) {
        return imageDao.imageLoad(imageName);
    }
}
