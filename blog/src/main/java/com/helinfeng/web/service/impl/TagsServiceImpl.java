package com.helinfeng.web.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.helinfeng.util.DateTimeUtil;
import com.helinfeng.util.UUIDUtil;
import com.helinfeng.web.dao.TagDao;
import com.helinfeng.web.domain.Tag;
import com.helinfeng.web.domain.Type;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TagsServiceImpl implements com.helinfeng.web.service.TagsService {
    @Resource
    private TagDao tagDao;
    @Override
    public PageInfo<Tag> queryTagsList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Tag> tagList = tagDao.selectTagsList();
        return new PageInfo<>(tagList);
    }

    @Override
    @Transactional
    public int saveTag(Tag tag) {
        int resultQuery = tagDao.selectTagByName(tag.getName());
        int resultInsert = 0;
        if(resultQuery <= 0){
            tag.setId(UUIDUtil.getUUID());
            tag.setInsertTime(DateTimeUtil.getSysTime());
            resultInsert = tagDao.insertTag(tag);
        }
        return resultInsert;
    }

    @Override
    @Transactional
    public void deletTag(String id) {
        tagDao.deleteTagById(id);
    }

    @Override
    @Transactional
    public int updateType(Type type) {
        int updateInt = 0;

        int resultByName = tagDao.selectTagByName(type.getName());
        if(resultByName == 0){
            int resultById = tagDao.selectTypeById(type.getId());
            if(resultById == 1){
                type.setInsertTime(DateTimeUtil.getSysTime());
                updateInt = tagDao.updateTypeById(type);
            }
        }else {
            updateInt = -1;
        }
        return updateInt;
    }

    @Override
    public List<Tag> queryTagList() {


        return tagDao.selectTagsList();
    }
}
