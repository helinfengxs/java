package com.helinfeng.web.service;

import com.github.pagehelper.PageInfo;
import com.helinfeng.web.domain.Tag;
import com.helinfeng.web.domain.Type;

import java.util.List;

public interface TagsService {
    PageInfo<Tag> queryTagsList(Integer pageNum, Integer pageSize);

    int saveTag(Tag tag);

    void deletTag(String id);

    int updateType(Type type);
    List<Tag> queryTagList();
}
