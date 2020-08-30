package com.helinfeng.web.dao;

import com.helinfeng.web.domain.Blog_Tag;

import java.util.List;

public interface Blog_TagDao {
    int insertBlog_Tag(List<Blog_Tag> blog_tagList);

    void deletByBlogId(String id);

    void deletByTagId(String id);

    void deletBlog_tagByTypeId(String id);
}
