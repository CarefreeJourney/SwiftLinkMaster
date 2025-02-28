package com.lucky.SwiftLinkMaster.admin.remote.dto.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/27 23:54
 * @Description: 短链接分页请求参数
 * @Position: com.lucky.SwiftLinkMaster.project.dto.req
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */
@Data
public class ShortLinkPageReqDTO extends Page{
    /**
     * 分组标识
     */
    private String gid;
}
