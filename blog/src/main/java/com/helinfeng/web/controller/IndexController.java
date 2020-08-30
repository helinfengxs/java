package com.helinfeng.web.controller;

import com.github.pagehelper.PageInfo;
import com.helinfeng.web.domain.Blog;
import com.helinfeng.web.domain.Image_type;
import com.helinfeng.web.domain.VoCountType;
import com.helinfeng.web.service.BlogService;
import com.helinfeng.web.service.Image_typeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.Map;

@Controller
public class IndexController {
    @Resource
    private BlogService blogService;
    @Resource
    private Image_typeService image_typeService;
//    @Resource
//    private TypeService typeService;
    @GetMapping("/")

    public String  index(Integer pageNum,Integer pageSize ,Model model){
        PageInfo<Blog> blogPageInfo = blogService.indexSelectAll(pageNum, pageSize);
        model.addAttribute("page",blogPageInfo);
        //type分类统计,显示前6条
        PageInfo<VoCountType> typePageInfo = blogService.indexTypeCount(1,6);
        model.addAttribute("typeList",typePageInfo);
        //标签统计,显示前10条
        PageInfo<VoCountType> tagCount= blogService.indexTagCount(1,10);
        model.addAttribute("tagList",tagCount);
        //最新推荐文章,显示前7条
        PageInfo<Blog> recommendCount = blogService.selectRecommend(1,7);
        model.addAttribute("recommendList",recommendCount);
        PageInfo<Image_type> imageTypeList =  image_typeService.typeCount(1, 10);
        model.addAttribute("image",imageTypeList);
        return "index";
    }

    /**
     @GetMapping("/a")
     @ResponseBody
     public PageInfo<Blog>  index1(Integer pageNum,Integer pageSize ,Model model){
     PageInfo<Blog> blogPageInfo = blogService.indexSelectAll(pageNum, pageSize);
     model.addAttribute("page",blogPageInfo);
     //type分类统计,显示前5条
     PageInfo<VoCountType> voCountTypePageInfo = blogService.indexTypeCount(pageNum,pageSize);

     PageInfo<VoCountType> tagCount= blogService.indexTagCount(pageNum,pageSize);
     PageInfo<Blog> recommendCount = blogService.selectRecommend(pageNum,pageSize);

     return blogPageInfo;
     }
             */

    @GetMapping("/search")
    public String search(String query, Model model,Integer pageNum,Integer pageSize){
        if(query == null){
            query="";
        }
        PageInfo<Blog> blogPageInfo = blogService.searchBlog(query,pageNum,pageSize);
        model.addAttribute("page",blogPageInfo);
        model.addAttribute("query",query);
        return "search";
    }
    @GetMapping("/blog/{id}")
    public String blogInfo(@PathVariable String id,Model model){

        Map<String,Object> bloginfo =  blogService.queryBlogInfo(id);
        model.addAttribute("page",bloginfo);
        System.out.println(bloginfo);
        return "info001.html";
    }
}
