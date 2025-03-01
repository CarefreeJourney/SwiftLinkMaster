package com.lucky.SwiftLinkMaster.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lucky.SwiftLinkMaster.admin.common.convention.result.Result;
import com.lucky.SwiftLinkMaster.admin.remote.ShortLinkRemoteService;
import com.lucky.SwiftLinkMaster.admin.remote.dto.req.ShortLinkCreateReqDTO;
import com.lucky.SwiftLinkMaster.admin.remote.dto.req.ShortLinkPageReqDTO;
import com.lucky.SwiftLinkMaster.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import com.lucky.SwiftLinkMaster.admin.remote.dto.resp.ShortLinkPageRespDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/25 23:24
 * @Description: 短链接控制层
 * @Position: com.lucky.SwiftLinkMaster.project.controller
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */
@RestController
public class ShortLinkController {
    // TODO 后续重构为 springcloud Feign 调用
    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };

    /**
     * 创建短链接
     * @param requestParam
     * @return
     */
    @PostMapping("/api/SwiftLinkMaster/short-link/admin/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam){
        return shortLinkRemoteService.createShortLink(requestParam);
    }

    /**
     * 分页查询短链接
     * @param requestParam
     * @return
     */
    @GetMapping("/api/SwiftLinkMaster/short-link/admin/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam){
        return shortLinkRemoteService.pageShortLink(requestParam);
    }
}
