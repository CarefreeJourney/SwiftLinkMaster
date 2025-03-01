package com.lucky.SwiftLinkMaster.project.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lucky.SwiftLinkMaster.project.common.database.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/25 22:45
 * @Description: 短链接数据库实体类
 * @Position: com.lucky.SwiftLinkMaster.project.dao.entity
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */
@TableName("t_link")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShortLinkDO extends BaseDO {

    /**
     * id
     */
    private Long id;

    /**
     * 域名
     */
    private String domain;

    /**
     * 短链接
     */
    private String shortUri;

    /**
     * 完整短链接
     */
    private String fullShortUrl;

    /**
     * 原始链接
     */
    private String originUrl;

    /**
     * 点击量
     */
    private Integer clickNum;

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 启用标识 （0：启用，默认就是启用）（1：未启用）
     */
    private Integer enableStatus;

    /**
     * 创建类型 1：控制台 0：接口
     */
    private Integer createdType;

    /**
     * 有效期类型 0：永久有效 1：用户自定义
     */
    private Integer validDateType;

    /**
     * 有效期
     */
    private Date validDate;

    /**
     * 描述 , DESCRIBE 是 MySQL 中的一个保留关键字，
     * 用于显示表结构的信息，故使用 MP得加上反引号
     */
    @TableField("`describe`")
    private String describe;

    /**
     * 网站标识
     */
    private String favicon;
}
