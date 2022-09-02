package com.crm.settings.mapper;


import com.crm.settings.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
* @author lenovo
* @description 针对表【tbl_user】的数据库操作Mapper
* @createDate 2022-08-25 09:33:27
* @Entity com.crm.entity.User
*/
@Mapper
public interface UserMapper {
    /**
     * 根据账户名和密码查询用户
     * @param map 参数
     * @return com.crm.entity.User
     */
    User selectUserByLoginActAndPwd(Map<String,Object> map);
    /**
     * 查询全部
     */
    List<User> selectAllUsers();
}
