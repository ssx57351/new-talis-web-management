package com.itheima.controller;

import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@Slf4j
public class MyLocalUpload {

    //本地存储文件
    /*@PostMapping("/upload")
    public Result upload(String username , Integer age , MultipartFile image) throws Exception {
        log.info("文件上传: {}, {}, {}", username, age, image);
        //获取原始文件名 - 1.jpg  123.0.0.jpg
        String originalFilename = image.getOriginalFilename();

        //构造唯一的文件名 (不能重复) - uuid(通用唯一识别码) de49685b-61c0-4b11-80fa-c71e95924018
        int index = originalFilename.lastIndexOf(".");
        String extname = originalFilename.substring(index);
        String newFileName = UUID.randomUUID().toString() + extname;
        log.info("新的文件名: {}", newFileName);

        //将文件存储在服务器的磁盘目录中 E:\images
        image.transferTo(new File("E:\\images\\"+newFileName));

        return Result.success();
    }*/
    @PostMapping("/localupload")
    public Result localUpload(MultipartFile multipartFile) throws IOException {

        String originalFilename = multipartFile.getOriginalFilename();
        String fileName = originalFilename.substring(originalFilename.lastIndexOf("."));
        //构造唯一的文件名 (不能重复) - uuid(通用唯一识别码) de49685b-61c0-4b11-80fa-c71e95924018
        String newFileName=UUID.randomUUID().toString()+fileName;
        log.info("新的文件名: {}", newFileName);
        //将文件存储在服务器的磁盘目录中 E:\image
        multipartFile.transferTo(new File("E:\\image\\"+newFileName));

        return Result.success();
    }
}
