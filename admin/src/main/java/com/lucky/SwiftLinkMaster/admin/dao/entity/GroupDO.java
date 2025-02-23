package com.lucky.SwiftLinkMaster.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lucky.SwiftLinkMaster.admin.common.database.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/23 16:43
 * @Description: 简链通分组实体
 * @Position: com.lucky.SwiftLinkMaster.admin.dao.entity
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */
@Data
@TableName("t_group")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupDO extends BaseDO {

    /**
     * id
     */
    private Long id;

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 分组名称
     */
    private String name;

    /**
     * 创建分组用户名
     */
    private String username;

    /**
     * 分组排序
     */
    private Integer sortOrder;

}
