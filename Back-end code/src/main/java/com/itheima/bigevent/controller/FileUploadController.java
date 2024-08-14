package com.itheima.bigevent.controller;

import com.itheima.bigevent.pojo.Result;
import com.itheima.bigevent.utils.AliOssUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws Exception {
        // 把文件内容存储到本地磁盘
        String originalFilename = file.getOriginalFilename();
        // 保证文件的名字是唯一的，从而防止文件覆盖
        String filename = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        // file.transferTo(new File("D:\\Brain\\TempFile\\"+filename));
        String url = AliOssUtil.uploadFile(filename,file.getInputStream());
        return Result.success(url);
    }
}
