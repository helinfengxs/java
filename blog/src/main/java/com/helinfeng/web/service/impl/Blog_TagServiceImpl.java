package com.helinfeng.web.service.impl;

import com.helinfeng.web.dao.Blog_TagDao;
import com.helinfeng.web.domain.Blog_Tag;
import com.helinfeng.web.service.Blog_TagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Blog_TagServiceImpl implements Blog_TagService {
    @Resource
    private Blog_TagDao blog_tagDao;
    @Override
    public int insertBlog_Tag(List<Blog_Tag> blog_tagList) {

        return blog_tagDao.insertBlog_Tag(blog_tagList);
    }

    @Override
    public void delBlog_Tag(String id) {
        blog_tagDao.deletByBlogId(id);
    }

    @Override
    public void deletBlog_Tag(String id) {
        blog_tagDao.deletByTagId(id);
    }

    @Override
    public void deletBlog_tagByTypeId(String id) {
        blog_tagDao.deletBlog_tagByTypeId(id);
    }
}
