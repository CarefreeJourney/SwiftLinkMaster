package com.lucky.SwiftLinkMaster.admin.dto.req;

import lombok.Data;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/22 16:12
 * @Description: 用户登录接口请求参数
 * @Position: com.lucky.SwiftLinkMaster.admin.dto.req
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */
@Data
public class UserLoginReqDTO {
    private String username;
    private String password;
}
