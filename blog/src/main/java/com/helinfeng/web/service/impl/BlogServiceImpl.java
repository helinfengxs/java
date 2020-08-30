package com.helinfeng.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.helinfeng.NotFoundException;
import com.helinfeng.util.MarkDownUtil;
import com.helinfeng.web.dao.BlogDao;
import com.helinfeng.web.dao.TagDao;
import com.helinfeng.web.dao.TypeDao;
import com.helinfeng.web.dao.UserDao;
import com.helinfeng.web.domain.*;
import com.helinfeng.web.service.BlogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService {
    @Resource
    private BlogDao blogDao;
    @Resource
    private TypeDao typeDao;
    @Resource
    private UserDao userDao;
    @Resource
    private TagDao tagDao;
    @Override
    public Map<String, Object> showBlogs() {
        List<Type> typeList = typeDao.selectTypeList();
        Map<String, Object> map = new HashMap<>();
        map.put("typeList",typeList);
        return map;
    }

    @Override
    public PageInfo<Blog> selectAll(Integer pageNum, Integer pageSize) {
        if (pageSize == null){
            pageSize = 5;
        }
        if (pageNum == null){
            pageNum = 1;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Blog> blogList = blogDao.selectBlogList();
        return new PageInfo<>(blogList);
    }


    @Override
    public PageInfo<Blog> indexSelectAll(Integer pageNum, Integer pageSize) {
        if (pageSize == null){
            pageSize = 5;
        }
        if (pageNum == null){
            pageNum = 1;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Blog> blogList = blogDao.selectBlogAndUser();
        return new PageInfo<>(blogList);
    }

    @Override
    public PageInfo<Blog> selectSearch(Blog blog, Integer page, Integer pageSize) {
        if (pageSize == null){
            pageSize = 5;
        }
        if (page == null){
            page = 1;
        }
        PageHelper.startPage(page,pageSize);
        List<Blog> blogList = blogDao.selectSearch(blog);
        return new PageInfo<>(blogList);
    }

    @Override
    public PageInfo<VoCountType> indexTypeCount(Integer pageNum, Integer pageSize) {
        if (pageSize == null){
            pageSize = 6;
        }
        if (pageNum == null){
            pageNum = 1;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<VoCountType> voCountType = blogDao.selectCountType();
        return new PageInfo<>(voCountType);
    }

    @Override
    public PageInfo<VoCountType> indexTagCount(Integer pageNum, Integer pageSize) {
        if (pageSize == null){
            pageSize = 10;
        }
        if (pageNum == null){
            pageNum = 1;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<VoCountType> countTag = blogDao.selectCountTag();
        return new PageInfo<>(countTag);
    }

    @Override
    public PageInfo<Blog> selectRecommend(Integer pageNum,Integer pageSize) {
        if (pageSize == null){
            pageSize = 7;
        }
        if (pageNum == null){
            pageNum = 1;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Blog> countTag = blogDao.selectRecommendBlog();
        return new PageInfo<>(countTag);
    }

    @Override
    public PageInfo<Blog> searchBlog(String query,Integer pageNum,Integer pageSize) {
        if (pageSize == null){
            pageSize = 7;
        }
        if (pageNum == null){
            pageNum = 1;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Blog> blogList =  blogDao.searchBlogByLike(query);
        return new PageInfo<>(blogList);
    }

    @Override
    public int saveBlog(Blog blog) {
        return blogDao.insertBlog(blog);
    }

    @Override
    public void delBlog(String id) {
        blogDao.deleteById(id);
    }

    @Override
    public Blog getBlog(String id) {

        return blogDao.selectBlogById(id);
    }

    @Override
    public int updateBlog(Blog blog) {
        int updateResult = blogDao.updateBlog(blog);
        return 0;
    }

    @Override
    public void delBlogByTypeId(String id) {
        blogDao.deleteByTypeId(id);
    }

    @Override
    public void deletBlogByTagId(String id) {
        blogDao.deletBlogByTagId(id);
    }

    @Override
    @Transactional
    public Map<String, Object> queryBlogInfo(String id) {
        Map<String, Object> blogMap = new HashMap<>();

        blogDao.updateViews(id);
        Blog blog = blogDao.selectBlogById(id);
        if(blog == null){
            throw new NotFoundException("博客资源部存在");
        }
        String content = blog.getContent();
        String markContent = MarkDownUtil.markDownToTtmlExtensions(content);
        blog.setContent(markContent);
        User user = userDao.selectById(id);
        List<Tag> tagList = tagDao.selectTagByBlogId(id);
        blogMap.put("blog",blog);
        blogMap.put("user",user);
        blogMap.put("tag",tagList);

        return blogMap;
    }

    @Override
    public PageInfo<Blog> selectBlogByTypeId(String id,Integer pageNum,Integer pageSize) {
        if (pageSize == null){
            pageSize = 7;
        }
        if (pageNum == null){
            pageNum = 1;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Blog> blogList =  blogDao.selectBlogByTypeId(id);
        return new PageInfo<>(blogList);

    }

    @Override
    public List<VoCountType> seleteAllcount(Integer pageNum, Integer pageSize) {

        return blogDao.selectCountTag();

    }

    @Override
    public PageInfo<Blog> selectBlog(String id, Integer pageNum, Integer pageSize) {
        if (pageSize == null){
            pageSize = 7;
        }
        if (pageNum == null){
            pageNum = 1;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Blog> blogList =  blogDao.selectBlogTagId(id);
        return new PageInfo<>(blogList);
    }

    @Override
    public List<String> countTime() {
        List<String>  listResult = blogDao.selectTimeCount();
        return listResult;
    }

    @Override
    public List<Blog> queryBlogByTime(String list) {
        List<Blog> blogList = blogDao.selectBlogByTime(list);
        return blogList;
    }

    @Override
    public Integer qeryAllCount() {
        Integer result = blogDao.selectAllCount();
        return result;
    }
}
