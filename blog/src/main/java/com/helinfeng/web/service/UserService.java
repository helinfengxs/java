package com.helinfeng.web.service;

import com.helinfeng.web.domain.User;

public interface UserService {
    User checkUser(String userName,String passWord);

    boolean addUser(User user);


}
