package com.lucky.SwiftLinkMaster.admin.controller;

import com.lucky.SwiftLinkMaster.admin.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

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
}
