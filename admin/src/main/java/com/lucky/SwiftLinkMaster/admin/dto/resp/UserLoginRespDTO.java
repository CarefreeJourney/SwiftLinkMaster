package com.lucky.SwiftLinkMaster.admin.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/22 16:10
 * @Description: 用户登录接口返回信息
 * @Position: com.lucky.SwiftLinkMaster.admin.dto.resp
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRespDTO {
    /**
     * 用户 Token
     */
    private String token;
}
