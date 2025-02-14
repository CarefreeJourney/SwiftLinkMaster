package com.lucky.SwiftLinkMaster.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.SwiftLinkMaster.admin.dao.entity.UserDO;
import com.lucky.SwiftLinkMaster.admin.dto.resp.UserRespDTO;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/11 21:55
 * @Description: 用户服务层
 * @Position: com.lucky.SwiftLinkMaster.admin.service
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */
public interface UserService extends IService<UserDO> {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */

    UserRespDTO getUserByUsername(String username);
}
