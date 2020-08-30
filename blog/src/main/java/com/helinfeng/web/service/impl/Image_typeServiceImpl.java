package com.helinfeng.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.helinfeng.util.DateTimeUtil;
import com.helinfeng.util.UUIDUtil;
import com.helinfeng.web.dao.Image_typeDao;
import com.helinfeng.web.domain.Image_type;
import com.helinfeng.web.service.Image_typeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Image_typeServiceImpl implements Image_typeService {
    @Resource
    private Image_typeDao image_typeDao;
    @Override
    @Transactional
    public void addImageType(Image_type type) {
        type.setId(UUIDUtil.getUUID());
        type.setCreateTime(DateTimeUtil.getSysTime());
        image_typeDao.insertImageType(type);
    }

    @Override
    public void deletTypeById(String id) {

    }

    @Override
    public List<Image_type> queryAll() {

        return image_typeDao.selectAll();
    }

    @Override
    public Image_type queryAddr(String id) {

        return image_typeDao.selectById(id);
    }

    @Override
    public PageInfo<Image_type> typeCount(Integer pageNum, Integer pageSize) {
        if (pageSize == null){
            pageSize = 5;
        }
        if (pageNum == null){
            pageNum = 1;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Image_type> image_types = image_typeDao.selectCount();
        return new PageInfo<>(image_types);
    }
}
