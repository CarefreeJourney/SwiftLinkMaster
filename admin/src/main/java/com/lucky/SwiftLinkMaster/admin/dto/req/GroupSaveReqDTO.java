package com.lucky.SwiftLinkMaster.admin.dto.req;

import lombok.Data;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/23 17:36
 * @Description: 新增短链接分组的创建参数
 * @Position: com.lucky.SwiftLinkMaster.admin.dto.req
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */
@Data
public class GroupSaveReqDTO {
    /**
     * 分组名称
     */
    private String name;
}
