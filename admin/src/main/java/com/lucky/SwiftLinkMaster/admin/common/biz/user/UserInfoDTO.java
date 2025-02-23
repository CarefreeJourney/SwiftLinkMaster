package com.lucky.SwiftLinkMaster.admin.common.biz.user;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/23 20:26
 * @Description: 用户信息实体
 * @Position: com.lucky.SwiftLinkMaster.admin.common.biz.user
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoDTO {
    @JSONField(name = "id")
    private String userId;
    private String username;
    private String realName;
}
