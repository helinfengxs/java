package com.helinfeng.web.controller;

import com.github.pagehelper.PageInfo;
import com.helinfeng.web.domain.Tag;
import com.helinfeng.web.domain.Type;
import com.helinfeng.web.service.BlogService;
import com.helinfeng.web.service.Blog_TagService;
import com.helinfeng.web.service.TagsService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

@Controller
@RequestMapping("/admin")
public class TagsConyroller {
    @Resource
    private TagsService tagsService;
    @Resource
    private BlogService blogService;
    @Resource
    private Blog_TagService blog_tagService;
    @GetMapping("/tagsList")
    public String tagsList(Integer pageNum, Integer pageSize, Model model){
        if (pageSize == null){
            pageSize = 5;
        }
        if (pageNum == null){
            pageNum = 1;
        }
        PageInfo<Tag> tagPageInfo = tagsService.queryTagsList(pageNum,pageSize);
        model.addAttribute("page",tagPageInfo);
        System.out.println(tagPageInfo);
        return "admin/tags";
    }
    @GetMapping("/tag/input")
    public String tagInput(){
        return "admin/tag_input";
    }
    @PostMapping("/tag/add")
    public String tagAdd(Tag tag, RedirectAttributes redirectAttributes){
        if(tag.getName() == null){
            redirectAttributes.addFlashAttribute("message","参数填写为空");
            return "redirect:/admin/tagsList";

        }
        int insertResult = tagsService.saveTag(tag);
        if(insertResult == 0){
            redirectAttributes.addFlashAttribute("message","标签已经存在");
            return "redirect:/admin/tagsList";
        }
        redirectAttributes.addFlashAttribute("message","添加成功");
        return "redirect:/admin/tagsList";
    }
    @GetMapping("/tag/{id}/delete")
    @Transactional
    public String deleteTag(@PathVariable String id,RedirectAttributes redirectAttributes){
        blog_tagService.deletBlog_Tag(id);
        blogService.deletBlogByTagId(id);
        tagsService.deletTag(id);
        redirectAttributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tagsList";
    }
    @GetMapping("/tag/eidtor/{id}")
    public String type_eidtor(@PathVariable String id, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("id",id);
        return "admin/tag_eidtor";
    }
    @PostMapping("/tag/eidtor")
    public String postEditor(Type type, RedirectAttributes redirectAttributes){
        int i = tagsService.updateType(type);
        if(i == 0){
            redirectAttributes.addFlashAttribute("message","修改失败");
            return "redirect:/admin/tagsList";
        }else if(i == -1){
            redirectAttributes.addFlashAttribute("message","修改标签重复");
            return "redirect:/admin/tagsList";
        }
        redirectAttributes.addFlashAttribute("message","修改成功");
        return "redirect:/admin/tagsList";
    }
}

