package com.helinfeng.web.controller;

import com.helinfeng.web.domain.Blog;
import com.helinfeng.web.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ArchivesController {
    @Resource
    private BlogService blogService;
    @RequestMapping("/archives")
    public String  archives(Model model){
        List<String> integerList =  blogService.countTime();
        List<Map<String,Object>> mapList = new ArrayList<>();
        System.out.println(integerList);
        if(integerList !=null){
            for(String  list:integerList){
                Map<String,Object> resultMap = new HashMap<>();
                List<Blog> blogs =blogService.queryBlogByTime(list);
                for(Blog blog:blogs){
                    String charSequence = (String) blog.getUpdate_time().subSequence(5, 10);
                    blog.setUpdate_time(charSequence);
                }
                resultMap.put("year",list);
                resultMap.put("blogs",blogs);
                mapList.add(resultMap);
            }
        }
        Integer result = blogService.qeryAllCount();
        model.addAttribute("page",mapList);
        model.addAttribute("total",result);
        return "archives";
    }
}
