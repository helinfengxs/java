package com.helinfeng.web.service;

import com.helinfeng.web.domain.Blog_Tag;

import java.util.List;

public interface Blog_TagService {
    int insertBlog_Tag(List<Blog_Tag> blog_tagList);

    void delBlog_Tag(String id);

    void deletBlog_Tag(String id);

    void deletBlog_tagByTypeId(String id);
}
