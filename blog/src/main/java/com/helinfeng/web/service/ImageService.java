package com.helinfeng.web.service;

import com.github.pagehelper.PageInfo;
import com.helinfeng.web.domain.Image;

import java.util.List;

public interface ImageService {
    PageInfo<Image> queryImges(Integer pageNum, Integer pageSize);

    void deleteImageById(String id);

    void deleteImageByTypeId(String id);

    void addImage(List<Image> imageList);

    Image delyTengxun(String id);

    PageInfo<Image> queryImagesById(String id, Integer pageNum, Integer pageSize);

//    PageInfo<Image> typeCount(Integer pageNum, Integer pageSize);

}
