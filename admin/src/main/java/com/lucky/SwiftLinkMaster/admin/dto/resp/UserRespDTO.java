package com.lucky.SwiftLinkMaster.admin.dto.resp;

import lombok.Data;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/12 13:15
 * @Description: 用户返回参数响应
 * @Position: com.lucky.SwiftLinkMaster.admin.dto.resp
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */
@Data
public class UserRespDTO {
    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;


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
