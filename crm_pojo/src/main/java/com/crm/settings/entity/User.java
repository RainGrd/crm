package com.crm.settings.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @Description User类
 * @TableName tbl_user
 */
@Data
@Alias("user")
public class User implements Serializable {
    /**
     * uuid
     *
     */
    @TableId(value = "id")
    private String id;

    /**
     * 账户
     */
    @TableField(value = "loginAct")
    private String loginAct;

    /**
     * 
     */
    @TableField(value = "name")
    private String name;

    /**
     * 密码不能采用明文存储，采用密文，MD5加密之后的数据
     */
    @TableField(value = "loginPwd")
    private String loginPwd;

    /**
     * 
     */
    @TableField(value = "email")
    private String email;

    /**
     * 失效时间为空的时候表示永不失效，失效时间为2018-10-10 10:10:10，则表示在该时间之前该账户可用。
     */
    @TableField(value = "expireTime")
    private String expireTime;

    /**
     * 锁定状态为空时表示启用，为0时表示锁定，为1时表示启用。
     */
    @TableField(value = "lockState")
    private String lockState;

    /**
     * 
     */
    @TableField(value = "deptNo")
    private String deptNo;

    /**
     * 允许访问的IP为空时表示IP地址永不受限，允许访问的IP可以是一个，也可以是多个，当多个IP地址的时候，采用半角逗号分隔。允许IP是192.168.100.2，表示该用户只能在IP地址为192.168.100.2的机器上使用。
     */
    @TableField(value = "allowIps")
    private String allowIps;

    /**
     * 
     */
    @TableField(value = "createTime")
    private String createTime;

    /**
     * 
     */
    @TableField(value = "createBy")
    private String createBy;

    /**
     * 编辑时间
     */
    @TableField(value = "editTime")
    private String editTime;

    /**
     * 
     */
    @TableField(value = "editBy")
    private String editBy;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}