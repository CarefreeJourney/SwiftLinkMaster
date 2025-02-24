package com.lucky.SwiftLinkMaster.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.SwiftLinkMaster.admin.dao.entity.GroupDO;
import com.lucky.SwiftLinkMaster.admin.dto.req.GroupUpdateReqDTO;
import com.lucky.SwiftLinkMaster.admin.dto.resp.GroupResponseDTO;

import java.util.List;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/23 16:51
 * @Description: 简链通分组服务层接口
 * @Position: com.lucky.SwiftLinkMaster.admin.service.impl
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */
public interface GroupService extends IService<GroupDO> {
    /**
     * 新增短链接分组
     * @param groupName
     */
    void saveGroup(String groupName);

    /**
     * 查询用户短链接分组集合
     * @return
     */
    List<GroupResponseDTO> listGroup();

    /**
     * 修改短链接分组
     * @param requestParam
     */
    void updateGroup(GroupUpdateReqDTO requestParam);
}
