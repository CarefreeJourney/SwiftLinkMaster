package com.lucky.SwiftLinkMaster.admin.controller;

import com.lucky.SwiftLinkMaster.admin.common.convention.result.Result;
import com.lucky.SwiftLinkMaster.admin.common.convention.result.Results;
import com.lucky.SwiftLinkMaster.admin.dto.req.GroupSaveReqDTO;
import com.lucky.SwiftLinkMaster.admin.dto.req.GroupUpdateReqDTO;
import com.lucky.SwiftLinkMaster.admin.dto.resp.GroupResponseDTO;
import com.lucky.SwiftLinkMaster.admin.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/23 16:58
 * @Description: 简链通分组控制层
 * @Position: com.lucky.SwiftLinkMaster.admin.controller
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */
@RestController
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    /**
     * 根据用户填写的短链接名称和用户上下文和随机生成的符合条件的gid，新增短链接分组
     * @param requestParam
     * @return
     */
    @PostMapping("/api/SwiftLinkMaster/v1/group")
    public Result<Void> save(@RequestBody GroupSaveReqDTO requestParam){
        groupService.saveGroup(requestParam.getName());
        return Results.success();
    }

    /**
     * 根据用户上下文返回短链接分组列表
     * @return
     */
    @GetMapping("/api/SwiftLinkMaster/v1/group")
    public Result<List<GroupResponseDTO>> listGroup(){
        return Results.success(groupService.listGroup());
    }

    /**
     * 根据短链接分组gid和用户上下文和分组名称，修改短链接的名称
     * @param requestParam
     * @return
     */
    @PutMapping("/api/SwiftLinkMaster/v1/group")
    public Result<Void> updateGroup(@RequestBody GroupUpdateReqDTO requestParam){
        groupService.updateGroup(requestParam);
        return Results.success();
    }
}
