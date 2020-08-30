package com.helinfeng.web.controller;


import com.github.pagehelper.PageInfo;
import com.helinfeng.web.domain.Blog;
import com.helinfeng.web.domain.VoCountType;
import com.helinfeng.web.service.BlogService;
import com.helinfeng.web.service.TypeService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@Transactional
public class TypeShowContrller {
    @Resource
    private TypeService typeService;
    @Resource
    private BlogService blogService;
    @RequestMapping("/type/{id}")
    public String type(@PathVariable String id, Integer pageNum, Integer pageSize, Model model){
        //type获取所有分类统计
        List<VoCountType> typeList = typeService.seletcAllCount();
        model.addAttribute("typelist",typeList);
        //获取分类总数
        int total = typeService.total();
        model.addAttribute("total",total);
        if("-1".equals(id)){
            id = typeList.get(0).getId();
        }
        PageInfo<Blog> blogList =  blogService.selectBlogByTypeId(id,pageNum,5);
        model.addAttribute("bloglist",blogList);
        model.addAttribute("activeTypeId",id);
        return "types";
    }
}
