package com.lucky.SwiftLinkMaster.project.dto.resp;

import lombok.Data;

/**
 * @Author: lcl
 * @CreateTime: 2025/3/1 15:04
 * @Description: 短链接分组查询返回参数（分组下的短链接总数目）
 * @Position: com.lucky.SwiftLinkMaster.project.dto.resp
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */
@Data
public class ShortLinkCountQueryRespDTO {
    /**
     * 分组标识 id
     */
    private String gid;
    /**
     * 组内的短链接数
     */
    private Integer shortLinkCount;
}
