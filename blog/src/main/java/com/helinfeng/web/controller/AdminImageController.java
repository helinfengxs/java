package com.helinfeng.web.controller;

import com.github.pagehelper.PageInfo;
import com.helinfeng.util.DateTimeUtil;
import com.helinfeng.util.UUIDUtil;
import com.helinfeng.web.domain.Image;
import com.helinfeng.web.domain.Image_type;
import com.helinfeng.web.service.ImageService;
import com.helinfeng.web.service.Image_typeService;
import com.helinfeng.web.service.impl.FileUploadServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminImageController {
    @Resource
    private FileUploadServiceImpl fileUploadService;
    @Resource
    private ImageService imageService;
    @Resource
    private Image_typeService image_typeService;
    @GetMapping("/imageList")
    public String photo(Integer pageNum, Integer pageSize, Model model){
        PageInfo<Image> imagePageInfo = imageService.queryImges(pageNum, pageSize);
        model.addAttribute("page",imagePageInfo);

        return "admin/image";
    }

    @GetMapping("/image/{id}/delete")
    @Transactional
    public String delet(@PathVariable String id , RedirectAttributes redirectAttributes){
        Image image = imageService.delyTengxun(id);
        fileUploadService.delete(image.getImageAddr().substring(56));
        imageService.deleteImageById(id);
        redirectAttributes.addFlashAttribute("message","删除博客成功");
        return "redirect:/admin/imageList";
    }
    @GetMapping("/imagetype/input")
    public String input(){

        return "admin/imagetypeinput";
    }
    @PostMapping("/imagetype/add")
    public String imageTypeDdd(Image_type type,MultipartFile file, RedirectAttributes redirectAttributes){
        System.out.println(file.getOriginalFilename());
        type.setFirstpicture(fileUploadService.upload(file));
        image_typeService.addImageType(type);
        redirectAttributes.addFlashAttribute("message","添加相册分类成功");
        return "redirect:/admin/imageList";
    }

    /**
     *  相册分类删除接口,只定义了service接口
     * @param id 相册分类id
     * @return 返回成功
     */
    @GetMapping("/imagetype/{id}/delete")
    @ResponseBody
    @Transactional
    public Map<String,Object> imageTypeDelete(@PathVariable String id){
        Image_type image_type = image_typeService.queryAddr(id);
        fileUploadService.delete(image_type.getFirstpicture().substring(56));
        image_typeService.deletTypeById(id);
        imageService.deleteImageByTypeId(id);
        Map<String,Object> map = new HashMap<>();
        map.put("删除数据成功","success");
        return map;
    }

    @GetMapping("/image/input")
    public String addPage(Model model){
        List<Image_type> imageTypeList = image_typeService.queryAll();
        model.addAttribute("page",imageTypeList);
        return "admin/imageAdd";
    }

    @RequestMapping("/upload")
    public  String  test( String imageTypeId, MultipartFile[] files){
        List<Image> imageList = new ArrayList<>();
        for(MultipartFile file:files){
            Image image = new Image();
            image.setId(UUIDUtil.getUUID());
            String upload = fileUploadService.upload(file);
            image.setImageAddr(upload);
            image.setImageTypeId(imageTypeId);
            image.setCreateTime(DateTimeUtil.getSysTime());
            imageList.add(image);
        }
        imageService.addImage(imageList);

        return "redirect:/admin/imageList";
    }
}
