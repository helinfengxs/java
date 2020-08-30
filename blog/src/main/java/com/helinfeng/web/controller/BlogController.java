package com.helinfeng.web.controller;

import com.github.pagehelper.PageInfo;
import com.helinfeng.util.DateTimeUtil;
import com.helinfeng.util.UUIDUtil;
import com.helinfeng.web.domain.Blog;
import com.helinfeng.web.domain.Blog_Tag;
import com.helinfeng.web.domain.Tag;
import com.helinfeng.web.domain.User;
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
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class BlogController {
    @Resource
    private BlogService blogService;
    @Resource
    private TagsService tagsService;
    @Resource
    private Blog_TagService blog_tagService;
    /**
     *
     * @return 博客列表展示
      */
    @GetMapping("/blogList")
    public String blogList(Integer pageNum, Integer pageSize, Model model){
        Map<String,Object> typeMap = blogService.showBlogs();
        model.addAttribute("page", typeMap);
        PageInfo<Blog> tagInfo  = blogService.selectAll(pageNum,pageSize);
        System.out.println(tagInfo);
        model.addAttribute("blogs",tagInfo);
        return "admin/blogs";
    }
    @PostMapping("/blogList/search")
    public String blogSearch(Blog blog , Integer page, Integer pageSize, Model model){
        if("true".equals(blog.getRecommend())){
            blog.setRecommend("1");
        }else{
            blog.setRecommend(null);
        }
        if(blog.getTitle().length() == 0){
            blog.setTitle(null);
        }
        if(blog.getType_id().length() == 0){
            blog.setType_id(null);
        }
        System.out.println(blog);
        PageInfo<Blog> tagInfo  = blogService.selectSearch(blog,page,pageSize);

        model.addAttribute("blogs",tagInfo);
        return "admin/blogs :: blogList";
    }
    @GetMapping("/blog/add")
    public String blogadd(Model model){
        model.addAttribute("blog",new Blog());
        Map<String,Object> typeMap = blogService.showBlogs();
        model.addAttribute("page",typeMap);
        List<Tag> tags = tagsService.queryTagList();
        model.addAttribute("tagList",tags);
        return "admin/blogs_input";
    }

    @PostMapping("/blog/save")
    @Transactional
    public String savePost(Blog blog, String tag_id, HttpSession session, RedirectAttributes redirectAttributes){
        int saveBlogResult = 0;
        blog.setId(UUIDUtil.getUUID());
        User user = (User) session.getAttribute("user");
        blog.setUser_id(user.getId());
        blog.setCreate_time(DateTimeUtil.getSysTime());
        blog.setUpdate_time(DateTimeUtil.getSysTime());
        if("on".equals(blog.getRecommend())){
            blog.setRecommend("1");
        }else {
            blog.setRecommend("0");
        }
        if("on".equals(blog.getIsSharStatement())){
            blog.setIsSharStatement("1");
        }else {
            blog.setIsSharStatement("0");
        }
        if("on".equals(blog.getIsAppreciation())){
            blog.setIsAppreciation("1");
        }else {
            blog.setIsAppreciation("0");
        }
        if("on".equals(blog.getIsCommentAbled())){
            blog.setIsCommentAbled("1");
        }else {
            blog.setIsCommentAbled("0");
        }
        System.out.println("***************************"+blog);
        saveBlogResult = blogService.saveBlog(blog);
        List<Blog_Tag> blog_tagList = new ArrayList<>();
        if(!"".equals(tag_id)){

            String[] split = tag_id.split(",");
            for(String s:split){
                Blog_Tag blog_tag  = new Blog_Tag();
                blog_tag.setId(UUIDUtil.getUUID());
                blog_tag.setBlog_id(blog.getId());
                blog_tag.setTag_id(s);
                blog_tagList.add(blog_tag);
            }
            blog_tagService.insertBlog_Tag(blog_tagList);
        }

        System.out.println("========================"+saveBlogResult);
        if(saveBlogResult == 1){
            redirectAttributes.addFlashAttribute("message","添加成功");
        }else{
            redirectAttributes.addFlashAttribute("message","添加失败");
        }

        return "redirect:/admin/blogList";
    }


    @GetMapping("/blog/delete/{id}")
    @Transactional
    public String   delBlog(@PathVariable String id, RedirectAttributes redirectAttributes){
            blog_tagService.delBlog_Tag(id);
            blogService.delBlog(id);
            redirectAttributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/blogList";
    }

    @GetMapping("/blog/editor/{id}")
    public String eidtorBtn(@PathVariable String id,Model model){
        Blog blog = blogService.getBlog(id);
        model.addAttribute("blog",blog);

        Map<String,Object> typeMap = blogService.showBlogs();
        model.addAttribute("page",typeMap);
        List<Tag> tags = tagsService.queryTagList();
        model.addAttribute("tagList",tags);
        return "admin/blogs_editor";
    }
    @PostMapping("/blog/update")
    @Transactional
    public String updatePost(Blog blog, String tag_id, HttpSession session, RedirectAttributes redirectAttributes){
        int saveBlogResult = 0;
        blog.setUpdate_time(DateTimeUtil.getSysTime());
        if("on".equals(blog.getRecommend())){
            blog.setRecommend("1");
        }else {
            blog.setRecommend("0");
        }
        if("on".equals(blog.getIsSharStatement())){
            blog.setIsSharStatement("1");
        }else {
            blog.setIsSharStatement("0");
        }
        if("on".equals(blog.getIsAppreciation())){
            blog.setIsAppreciation("1");
        }else {
            blog.setIsAppreciation("0");
        }
        if("on".equals(blog.getIsCommentAbled())){
            blog.setIsCommentAbled("1");
        }else {
            blog.setIsCommentAbled("0");
        }
        saveBlogResult = blogService.updateBlog(blog);
        System.out.println("#################################"+saveBlogResult);
        blog_tagService.delBlog_Tag(blog.getId());
        List<Blog_Tag> blog_tagList = new ArrayList<>();
        if(!"".equals(tag_id)){

            String[] split = tag_id.split(",");
            for(String s:split){
                Blog_Tag blog_tag  = new Blog_Tag();
                blog_tag.setId(UUIDUtil.getUUID());
                blog_tag.setBlog_id(blog.getId());
                blog_tag.setTag_id(s);
                blog_tagList.add(blog_tag);
            }
            blog_tagService.insertBlog_Tag(blog_tagList);
        }


        if(saveBlogResult == 0){
            redirectAttributes.addFlashAttribute("message","修改成功");
        }else{
            redirectAttributes.addFlashAttribute("message","修改失败");
        }

        return "redirect:/admin/blogList";
    }
}
