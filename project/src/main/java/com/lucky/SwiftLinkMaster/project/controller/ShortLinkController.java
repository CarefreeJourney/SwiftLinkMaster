package com.lucky.SwiftLinkMaster.project.controller;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lucky.SwiftLinkMaster.project.common.convention.result.Result;
import com.lucky.SwiftLinkMaster.project.common.convention.result.Results;
import com.lucky.SwiftLinkMaster.project.dto.req.ShortLinkCreateReqDTO;
import com.lucky.SwiftLinkMaster.project.dto.req.ShortLinkPageReqDTO;
import com.lucky.SwiftLinkMaster.project.dto.resp.ShortLinkCountQueryRespDTO;
import com.lucky.SwiftLinkMaster.project.dto.resp.ShortLinkCreateRespDTO;
import com.lucky.SwiftLinkMaster.project.dto.resp.ShortLinkPageRespDTO;
import com.lucky.SwiftLinkMaster.project.service.ShortLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        String requestString = JSON.toJSONString(requestParam);
        return Results.success(shortLinkService.createShortLink(requestParam));
    }

    /**
     * 分页查询短链接
     * @param requestParam
     * @return
     */
    @GetMapping("/api/SwiftLinkMaster/short-link/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam){
        return Results.success(shortLinkService.pageShortLink(requestParam));
    }

    /**
     * 查询短链接分组内数量
     * @return
     */
    @GetMapping("/api/SwiftLinkMaster/short-link/v1/count")
    public Result<List<ShortLinkCountQueryRespDTO>> listGroupShortLinkCount(@RequestParam("requestParam") List<String> requestParam){
        return Results.success(shortLinkService.listGroupShortLinkCount(requestParam));

    }
}
