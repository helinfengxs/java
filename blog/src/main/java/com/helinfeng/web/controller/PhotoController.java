package com.helinfeng.web.controller;


import com.helinfeng.web.service.impl.FileUploadServiceImpl;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class PhotoController {
    @Resource
    private FileUploadServiceImpl fileUploadService;
//    @Resource
//    private ImageService imageService;
//    @RequestMapping("/upload")
//    public  @ResponseBody List<Image>  test( String imageTypeId, MultipartFile[] files){
//        List<Image> imageList = new ArrayList<>();
//        for(MultipartFile file:files){
//            Image image = new Image();
//            image.setId(UUIDUtil.getUUID());
//            image.setImageAddr(fileUploadService.upload(file));
//            image.setImageTypeId(imageTypeId);
//            image.setCreateTime(DateTimeUtil.getSysTime());
//            imageList.add(image);
//        }
//        imageService.addImage(imageList);
//        return imageList;
//    }
}
