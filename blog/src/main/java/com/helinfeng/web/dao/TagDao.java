package com.helinfeng.web.dao;

import com.helinfeng.web.domain.Tag;
import com.helinfeng.web.domain.Type;

import java.util.List;

public interface TagDao {
    List<Tag> selectTagsList();

    int selectTagByName(String name);

    int insertTag(Tag tag);

    void deleteTagById(String id);

    int selectTypeById(String id);

    int updateTypeById(Type type);

    List<Tag> selectTagByBlogId(String id);
}
