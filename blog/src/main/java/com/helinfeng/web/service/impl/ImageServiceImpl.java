package com.helinfeng.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.helinfeng.web.dao.ImageDao;
import com.helinfeng.web.domain.Image;
import com.helinfeng.web.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service
public class ImageServiceImpl implements ImageService {
    @Resource
    private ImageDao imageDao;
    @Override
    public PageInfo<Image> queryImges(Integer pageNum, Integer pageSize) {
        if (pageSize == null){
            pageSize = 5;
        }
        if (pageNum == null){
            pageNum = 1;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Image> images = imageDao.selectAll();
        return new PageInfo<>(images);
    }

    @Override
    public void deleteImageById(String id) {
        imageDao.deleteImageById(id);
    }

    @Override
    public void deleteImageByTypeId(String id) {

    }

    @Override
    @Transactional
    public void addImage(List<Image> imageList) {

        imageDao.insertImage(imageList);
    }

    @Override
    public Image delyTengxun(String id) {

        return imageDao.selectById(id);
    }

    @Override
    public PageInfo<Image> queryImagesById(String id, Integer pageNum, Integer pageSize) {
        if (pageSize == null){
            pageSize = 16;
        }
        if (pageNum == null){
            pageNum = 1;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Image> images =  imageDao.selectByTypeId(id);
        return new PageInfo<>(images);
    }
}
