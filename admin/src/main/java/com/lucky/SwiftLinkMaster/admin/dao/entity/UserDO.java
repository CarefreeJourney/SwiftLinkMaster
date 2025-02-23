package com.lucky.SwiftLinkMaster.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lucky.SwiftLinkMaster.admin.common.database.BaseDO;
import lombok.Data;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/11 21:29
 * @Description: UserDO：即用户持久层对象（Duration Object）
 * @Position: com.lucky.SwiftLinkMaster.admin.dao.entity
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */
@Data
@TableName("t_user")
public class UserDO extends BaseDO {
    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 注销时间戳
     */
    private Long deletionTime;

}
