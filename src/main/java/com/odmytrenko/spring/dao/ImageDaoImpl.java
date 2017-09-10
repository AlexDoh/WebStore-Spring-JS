package com.odmytrenko.spring.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Repository
public class ImageDaoImpl implements ImageDao {

    @Value("${imageStore}")
    String filePath;

    @Override
    public boolean imageUpload(CommonsMultipartFile file, String imageName) {
        try {
            byte barr[] = file.getBytes();

            BufferedOutputStream bout = new BufferedOutputStream(
                    new FileOutputStream(filePath + File.separator + imageName + ".jpg"));
            bout.write(barr);
            bout.flush();
            bout.close();
            return true;
        } catch (Exception e) {
            throw new RuntimeException("There was problem with uploading file " + e);
        }
    }

    @Override
    public File imageLoad(String imageName) {
        return new File(filePath, imageName + ".jpg");
    }

}
