package com.helinfeng.web.dao;

import com.helinfeng.web.domain.User;

public interface UserDao {
    User queryUser(String userName, String passWord);

    int insertUser(User user);

    User selectById(String id);
}
