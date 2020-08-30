package com.helinfeng.web.service;

import com.github.pagehelper.PageInfo;
import com.helinfeng.web.domain.Blog;
import com.helinfeng.web.domain.VoCountType;

import java.util.List;
import java.util.Map;

public interface BlogService {
    Map<String, Object> showBlogs();

    PageInfo<Blog> selectAll(Integer pageNum, Integer pageSize);
    PageInfo<Blog> indexSelectAll(Integer pageNum, Integer pageSize);

    PageInfo<Blog> selectSearch(Blog voBlog, Integer page, Integer pageSize);


    int saveBlog(Blog blog);

    void delBlog(String id);

    Blog getBlog(String id);

    int updateBlog(Blog blog);

    PageInfo<VoCountType> indexTypeCount(Integer pageNum, Integer pageSize);

    void delBlogByTypeId(String id);

    void deletBlogByTagId(String id);

    PageInfo<VoCountType> indexTagCount(Integer pageNum, Integer pageSize);

    PageInfo<Blog> selectRecommend(Integer pageNum,Integer pageSize);

    PageInfo<Blog> searchBlog(String query,Integer pageNum,Integer pageSize);

    Map<String, Object> queryBlogInfo(String id);

    PageInfo<Blog> selectBlogByTypeId(String id,Integer pageNum,Integer PageSize);

    List<VoCountType> seleteAllcount(Integer pageNum, Integer pageSize);

    PageInfo<Blog> selectBlog(String id, Integer pageNum, Integer pageSize);

    List<String> countTime();

    List<Blog> queryBlogByTime(String list);

    Integer qeryAllCount();

}
