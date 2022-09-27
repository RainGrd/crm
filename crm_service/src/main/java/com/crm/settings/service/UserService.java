package com.crm.settings.service;

import com.crm.settings.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author lenovo
 * @description 针对表【tbl_user】的数据库操作Service
 * @createDate 2022-08-25 09:33:27
 */
public interface UserService {
    /**
     * 查询用户
     */
    User queryUserLoginActAndPwd(Map<String,String> map);
    /**
     * 查询全部用户
     */
    List<User> queryAllUsers();
}
