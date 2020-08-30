package com.helinfeng.web.service.impl;

import com.helinfeng.util.QCloudUtil;
import com.helinfeng.web.service.FileUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Resource
    private QCloudUtil qCloudUtil;
    @Override
    public String upload(MultipartFile multipartFile) {
        return qCloudUtil.upload(multipartFile);
    }

    @Override
    public String upload(File file) {
        return qCloudUtil.upload(file);
    }

    @Override
    public void delete(String key) {
        qCloudUtil.delete(key);
    }
}
