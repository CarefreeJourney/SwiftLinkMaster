package com.lucky.SwiftLinkMaster.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.SwiftLinkMaster.project.dao.entity.ShortLinkDO;
import com.lucky.SwiftLinkMaster.project.dto.req.ShortLinkCreateReqDTO;
import com.lucky.SwiftLinkMaster.project.dto.resp.ShortLinkCreateRespDTO;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/25 22:50
 * @Description: 短链接服务层接口
 * @Position: com.lucky.SwiftLinkMaster.project.service
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */
public interface ShortLinkService extends IService<ShortLinkDO> {
    /**
     * 创建短链接
     * @param requestParam 创建短链接请求参数
     * @return
     */
    ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam);

}
