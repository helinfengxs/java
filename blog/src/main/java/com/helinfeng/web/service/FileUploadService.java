package com.helinfeng.web.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface FileUploadService {
    /**
     * 处理浏览器文件上传请求
     * @param multipartFile
     * @return
     */
    String upload(MultipartFile multipartFile);

    /**
     * 处理普通文件上传
     * @param file
     * @return
     */
    String upload(File file);
    void delete(String key);
}
