package com.helinfeng.web.controller;

import com.github.pagehelper.PageInfo;
import com.helinfeng.web.domain.Image;
import com.helinfeng.web.domain.Image_type;
import com.helinfeng.web.service.ImageService;
import com.helinfeng.web.service.Image_typeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;

@Controller
public class ImageController {
    @Resource
    private Image_typeService image_typeService;
    @Resource
    private ImageService imageService;
    @GetMapping("/imageType")
    public String getImageType(Integer pageNum, Integer pageSize,Model model){
        PageInfo<Image_type> imageTypeList =  image_typeService.typeCount(pageNum, pageSize);
        model.addAttribute("page",imageTypeList);
        PageInfo<Image> imagePageInfo = imageService.queryImges(pageNum, pageSize);
        model.addAttribute("total",imagePageInfo);

        return "photoType";
    }
    @GetMapping("/image/{id}")
    public String getImageInfo(@PathVariable String id, Integer pageNum, Integer pageSize, Model model){
        PageInfo<Image> pageInfo = imageService.queryImagesById(id,pageNum,pageSize);
        model.addAttribute("page",pageInfo);
        Image_type image_type = image_typeService.queryAddr(id);
       model.addAttribute("type",image_type);
        return "image";
    }
}
