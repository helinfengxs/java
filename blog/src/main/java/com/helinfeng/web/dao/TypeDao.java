package com.helinfeng.web.dao;

import com.helinfeng.web.domain.Type;

import java.util.List;

public interface TypeDao {
    int saveByType(Type type);

    int selectTypeByName(String name);
    List<Type> selectTypeList();
    int deleteTypeById(String id);
    List<Type> selectTypePage();

    int selectTypeById(String id);

    int updateTypeById(Type type);

    List<Object> countTotal();
}
