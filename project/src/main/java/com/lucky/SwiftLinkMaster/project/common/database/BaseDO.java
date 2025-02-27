package com.lucky.SwiftLinkMaster.project.common.database;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/23 17:46
 * @Description: 数据库持久层基础属性 -- 创建时间+更新时间+软删除标识
 * @Position: com.lucky.SwiftLinkMaster.admin.common.database
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */
@Data
public class BaseDO {
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 删除标识 0：未删除 1：已删除
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer delFlag;
}
