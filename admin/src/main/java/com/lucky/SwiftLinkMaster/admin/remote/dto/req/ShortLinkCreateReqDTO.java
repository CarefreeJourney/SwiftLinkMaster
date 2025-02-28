package com.lucky.SwiftLinkMaster.admin.remote.dto.req;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/26 18:56
 * @Description: 短链接创建请求对象
 * @Position: com.lucky.SwiftLinkMaster.project.dto.req
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */
@Data
public class ShortLinkCreateReqDTO {
    /**
     * 域名
     */
    private String domain;

    /**
     * 原始链接
     */
    private String originUrl;

    /**
     * 分组标识
     */
    private String gid;

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
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date validDate;

    /**
     * 描述 , DESCRIBE 是 MySQL 中的一个保留关键字，
     * 用于显示表结构的信息，故使用 MP得加上反引号
     */
    @TableField("`describe`")
    private String describe;

}
