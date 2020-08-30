package com.helinfeng.web.service;

import com.github.pagehelper.PageInfo;
import com.helinfeng.web.domain.Image_type;

import java.util.List;

public interface Image_typeService {

    void addImageType(Image_type type);

    void deletTypeById(String id);

    List<Image_type> queryAll();

    Image_type queryAddr(String id);

    PageInfo<Image_type> typeCount(Integer pageNum, Integer pageSize);
}
