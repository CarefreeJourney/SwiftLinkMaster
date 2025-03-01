package com.lucky.SwiftLinkMaster.admin.dto.resp;

import lombok.Data;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/23 20:03
 * @Description: 简链通分组查询返回实体
 * @Position: com.lucky.SwiftLinkMaster.admin.dto.resp
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */
@Data
public class GroupResponseDTO {
    /**
     * 分组标识
     */
    private String gid;

    /**
     * 分组名称
     */
    private String name;


    /**
     * 分组排序
     */
    private Integer sortOrder;
}
