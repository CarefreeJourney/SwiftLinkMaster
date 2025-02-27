package com.lucky.SwiftLinkMaster.project.controller;

import com.lucky.SwiftLinkMaster.project.common.convention.result.Result;
import com.lucky.SwiftLinkMaster.project.common.convention.result.Results;
import com.lucky.SwiftLinkMaster.project.dto.req.ShortLinkCreateReqDTO;
import com.lucky.SwiftLinkMaster.project.dto.resp.ShortLinkCreateRespDTO;
import com.lucky.SwiftLinkMaster.project.service.ShortLinkService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class ShortLinkController {

    private final ShortLinkService shortLinkService;

    @PostMapping("/api/SwiftLinkMaster/short-link/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam){
        return Results.success(shortLinkService.createShortLink(requestParam));
    }
}
