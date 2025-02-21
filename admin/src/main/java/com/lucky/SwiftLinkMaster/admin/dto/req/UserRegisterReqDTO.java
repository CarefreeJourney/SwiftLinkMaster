package com.lucky.SwiftLinkMaster.admin.dto.req;

import lombok.Data;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/15 10:27
 * @Description: 用户注册请求参数
 * @Position: com.lucky.SwiftLinkMaster.admin.dto.req
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */
@Data
public class UserRegisterReqDTO {
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

}
