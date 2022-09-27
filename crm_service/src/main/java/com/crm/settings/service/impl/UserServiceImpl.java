package com.crm.settings.service.impl;

import com.crm.settings.entity.User;
import com.crm.settings.mapper.UserMapper;
import com.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author lenovo
* @description 针对表【tbl_user】的数据库操作Service实现
* @createDate 2022-08-25 09:33:27
*/
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User queryUserLoginActAndPwd(Map<String,String> map) {
        return userMapper.selectUserByLoginActAndPwd(map);
    }

    @Override
    public List<User> queryAllUsers() {
        return userMapper.selectAllUsers();
    }
}
