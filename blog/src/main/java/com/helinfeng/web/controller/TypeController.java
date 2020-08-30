package com.helinfeng.web.controller;

import com.github.pagehelper.PageInfo;
import com.helinfeng.web.domain.Type;
import com.helinfeng.web.service.BlogService;
import com.helinfeng.web.service.Blog_TagService;
import com.helinfeng.web.service.TypeService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

@Controller
@RequestMapping("/admin")
public class TypeController {
    @Resource
    private TypeService typeService;
    @Resource
    private BlogService blogService;
    @Resource
    private Blog_TagService blog_tagService;
//    @GetMapping("/page")
//    @ResponseBody
//    public PageInfo<Type> test(Integer pageNum, Integer pageSize){
//        System.out.println(typeService.selectTypePage(pageNum,pageSize));
//        return typeService.selectTypePage(pageNum,pageSize);
//    }
    @GetMapping("/typesList")
    public String types(Integer pageNum, Integer pageSize, Model model){
        if (pageSize == null){
            pageSize = 5;
        }
        if (pageNum == null){
            pageNum = 1;
        }
        PageInfo<Type> typePageInfo = typeService.selectTypePage(pageNum, pageSize);

        model.addAttribute("page",typePageInfo);
        return "admin/types";
    }
    @GetMapping("/types/{id}/delete")
    @Transactional
    public String types(@PathVariable String id , RedirectAttributes redirectAttributes){

        blog_tagService.deletBlog_tagByTypeId(id);
        blogService.delBlogByTypeId(id);
        typeService.deleteType(id);

        redirectAttributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/typesList";
    }

    /**
     *
     * @return 返回添加分类页面
     */
    @GetMapping("/types/input")
    public String input(){

        return "admin/type_input";
    }

    @PostMapping("/types/add")
    public String post(Type type, RedirectAttributes redirectAttributes){
        if(type.getName() == null){
            redirectAttributes.addFlashAttribute("message","参数填写为空");
            return "redirect:/admin/typesList";

        }
        int insertResult = typeService.saveType(type);
        if(insertResult == 0){
            redirectAttributes.addFlashAttribute("message","分类已经存在");
            return "redirect:/admin/typesList";
        }
        redirectAttributes.addFlashAttribute("message","添加成功");
        return "redirect:/admin/typesList";
    }
    @GetMapping("/types/eidtor/{id}")
    public String type_eidtor(@PathVariable String id, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("id",id);
        return "admin/type_eidtor";
    }
    @PostMapping("/types/eidtor")
    public String postEditor(Type type, RedirectAttributes redirectAttributes){
        int i = typeService.updateType(type);
        if(i == 0){
            redirectAttributes.addFlashAttribute("message","修改失败");
            return "redirect:/admin/typesList";
        }else if(i == -1){
            redirectAttributes.addFlashAttribute("message","修改分类重复");
            return "redirect:/admin/typesList";
        }
        redirectAttributes.addFlashAttribute("message","添加成功");
        return "redirect:/admin/typesList";
    }

}
