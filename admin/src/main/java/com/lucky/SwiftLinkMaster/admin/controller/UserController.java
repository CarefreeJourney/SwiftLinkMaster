package com.lucky.SwiftLinkMaster.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import com.lucky.SwiftLinkMaster.admin.common.convention.result.Result;
import com.lucky.SwiftLinkMaster.admin.common.convention.result.Results;
import com.lucky.SwiftLinkMaster.admin.dto.resp.UserActualRespDTO;
import com.lucky.SwiftLinkMaster.admin.dto.resp.UserRespDTO;
import com.lucky.SwiftLinkMaster.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/11 20:49
 * @Description: 用户管理控制层
 * @Position: com.lucky.SwiftLinkMaster.admin.controller
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */
@RestController
//@AllArgsConstructor
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    /**
     * 根据用户名查询用户信息，手机号已脱敏
     * @param username
     * @return
     */
    @GetMapping("/api/SwiftLinkMaster/v1/user/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable("username") String username){
        UserRespDTO result = userService.getUserByUsername(username);
        return Results.success(result);
    }
    /**
     * 根据用户名查询用户信息，手机号未脱敏
     * @param username
     * @return
     */
    @GetMapping("/api/SwiftLinkMaster/v1/actual/user/{username}")
    public Result<UserActualRespDTO> getActualUserByUsername(@PathVariable("username") String username){
        UserRespDTO result = userService.getUserByUsername(username);
        return Results.success(BeanUtil.toBean(result,UserActualRespDTO.class));
    }
}
