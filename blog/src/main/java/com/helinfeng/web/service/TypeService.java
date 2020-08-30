package com.helinfeng.web.service;

import com.github.pagehelper.PageInfo;
import com.helinfeng.web.domain.Type;
import com.helinfeng.web.domain.VoCountType;

import java.util.List;


public interface TypeService {
    int saveType(Type type);
    Type getType(String id);
    int updateType(Type type);
    void deleteType(String id);
    PageInfo<Type> selectTypePage(int pageNum, int pageSize);

    List<VoCountType> seletcAllCount();

    int total();

}
