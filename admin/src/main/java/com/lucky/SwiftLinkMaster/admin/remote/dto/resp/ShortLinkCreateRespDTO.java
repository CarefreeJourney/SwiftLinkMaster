package com.lucky.SwiftLinkMaster.admin.remote.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/26 19:01
 * @Description: 短链接创建响应对象
 * @Position: com.lucky.SwiftLinkMaster.project.dto.resp
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShortLinkCreateRespDTO {
    /**
     * 分组 id
     */
    private String gid;

    /**
     * 完整短链接
     */
    private String fullShortUrl;

    /**
     * 原始链接
     */
    private String originUrl;

}
