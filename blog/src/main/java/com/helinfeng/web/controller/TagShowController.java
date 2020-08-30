package com.helinfeng.web.controller;

import com.github.pagehelper.PageInfo;
import com.helinfeng.web.domain.Blog;
import com.helinfeng.web.domain.VoCountType;
import com.helinfeng.web.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class TagShowController {
    @Resource
    private  BlogService blogService;

    @RequestMapping("/tag/{id}")
    public String type(@PathVariable String id, Integer pageNum, Integer pageSize, Model model){
        List<VoCountType> tagCount= blogService.seleteAllcount(pageNum,pageSize);
        model.addAttribute("tags",tagCount);
        if("-1".equals(id)){
            id=tagCount.get(0).getId();
        }
        model.addAttribute("total",tagCount.size());
        PageInfo<Blog> blogPageInfo =  blogService.selectBlog(id,pageNum,pageSize);
        model.addAttribute("page",blogPageInfo);

        model.addAttribute("activeTagId",id);

        return "tags";
    }
}
