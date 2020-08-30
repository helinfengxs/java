package com.helinfeng.web.dao;

import com.helinfeng.web.domain.Image;

import java.util.List;

public interface ImageDao {
    List<Image> selectAll();

    void deleteImageById(String id);

    void insertImage(List<Image> imageList);

    Image selectById(String id);


    List<Image> selectByTypeId(String id);
}
