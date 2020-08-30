package com.helinfeng.web.service.impl;

import com.helinfeng.util.DateTimeUtil;
import com.helinfeng.util.MD5Util;
import com.helinfeng.util.UUIDUtil;
import com.helinfeng.web.dao.UserDao;
import com.helinfeng.web.domain.User;
import com.helinfeng.web.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Override
    public User checkUser(String userName,String passWord) {


        passWord = MD5Util.getMD5(passWord);
        return userDao.queryUser(userName,passWord);
    }

    @Override
    public boolean addUser(User user) {
        String passWord = MD5Util.getMD5(user.getPassWord());
        User checkUser = userDao.queryUser(user.getUserName(), passWord);
        boolean result = true;
        if(checkUser == null){
            String [] attr = {"../static/images/attr.jpg","../static/images/attr01.jpg","../static/images/attr02.jpg","../static/images/attr03.jpg","../static/images/attr04.jpg",};
            int max=5,min=1;
            int ran2 = (int) (Math.random()*(max-min)+min);
            user.setId(UUIDUtil.getUUID());
            user.setAvatar(attr[ran2]);
            user.setPassWord(MD5Util.getMD5(user.getPassWord()));
            user.setCreate_time(DateTimeUtil.getSysTime());
            user.setUpdate_time(DateTimeUtil.getSysTime());
            int count = userDao.insertUser(user);
            if(count <= 0){
                result = false;
            }
        }else{
            result =false;
        }

        return result;
    }
}
