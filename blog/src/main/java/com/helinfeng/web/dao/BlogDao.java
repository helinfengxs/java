package com.helinfeng.web.dao;

import com.helinfeng.web.domain.Blog;
import com.helinfeng.web.domain.VoCountType;

import java.util.List;

public interface BlogDao {
    List<Blog> selectBlogList();

    List<Blog> selectSearch(Blog blog);

    int insertBlog(Blog blog);

    void deleteById(String id);

    Blog selectBlogById(String id);

    int updateBlog(Blog blog);

    List<Blog> selectBlogAndUser();

    List<VoCountType> selectCountType();

    void deleteByTypeId(String id);

    void deletBlogByTagId(String id);

    List<VoCountType> selectCountTag();

    List<Blog> selectRecommendBlog();

    List<Blog> searchBlogByLike(String query);

    void updateViews(String id);

    List<Blog> selectBlogByTypeId(String id);

    List<Blog> selectBlogTagId(String id);

    List<String> selectTimeCount();


    List<Blog> selectBlogByTime(String list);

    Integer selectAllCount();

}
