package com.lucky.SwiftLinkMaster.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.SwiftLinkMaster.admin.dao.entity.GroupDO;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/23 16:51
 * @Description: 简链通分组服务层接口
 * @Position: com.lucky.SwiftLinkMaster.admin.service.impl
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */
public interface GroupService extends IService<GroupDO> {
    void saveGroup(String groupName);
}
