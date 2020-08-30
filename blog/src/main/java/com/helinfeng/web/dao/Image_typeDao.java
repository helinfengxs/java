package com.helinfeng.web.dao;

import com.helinfeng.web.domain.Image_type;

import java.util.List;

public interface Image_typeDao {
    void insertImageType(Image_type type);

    List<Image_type> selectAll();

    List<Image_type> selectCount();

    Image_type selectById(String id);
}
