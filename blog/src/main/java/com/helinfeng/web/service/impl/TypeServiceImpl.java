package com.helinfeng.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.helinfeng.util.DateTimeUtil;
import com.helinfeng.util.UUIDUtil;
import com.helinfeng.web.dao.BlogDao;
import com.helinfeng.web.dao.TypeDao;
import com.helinfeng.web.domain.Type;
import com.helinfeng.web.domain.VoCountType;
import com.helinfeng.web.service.TypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Resource
    private TypeDao typeDao;
    @Resource
    private BlogDao blogDao;
    @Override
    @Transactional
    public int saveType(Type type) {
        int i = typeDao.selectTypeByName(type.getName());
        int result = 0;
        if(i <= 0){
            type.setId(UUIDUtil.getUUID());
            type.setInsertTime(DateTimeUtil.getSysTime());
            result = typeDao.saveByType(type);
        }

        return result;
    }

    @Override
    public Type getType(String id) {
        return null;
    }
    @Transactional
    @Override
    public int updateType( Type type) {
        int updateInt = 0;

        int resultByName = typeDao.selectTypeByName(type.getName());
        if(resultByName == 0){
            int resultById = typeDao.selectTypeById(type.getId());
            if(resultById == 1){
                type.setInsertTime(DateTimeUtil.getSysTime());
                updateInt = typeDao.updateTypeById(type);
            }
        }else {
            updateInt = -1;
        }
        return updateInt;
    }

    @Override
    @Transactional
    public void deleteType(String id) {
        int i = typeDao.deleteTypeById(id);

    }

    @Override
    public PageInfo<Type> selectTypePage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Type> typeList = typeDao.selectTypeList();
        return new PageInfo<>(typeList);
    }

    @Override
    public List<VoCountType> seletcAllCount() {
        List<VoCountType> voCountType = blogDao.selectCountType();
        return voCountType;
    }

    @Override
    public int total() {
        List<Object> count = typeDao.countTotal();

        return count.size();
    }
}
