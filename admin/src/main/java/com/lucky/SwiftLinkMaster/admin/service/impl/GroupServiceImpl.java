package com.lucky.SwiftLinkMaster.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.SwiftLinkMaster.admin.dao.entity.GroupDO;
import com.lucky.SwiftLinkMaster.admin.dao.mapper.GroupMapper;
import com.lucky.SwiftLinkMaster.admin.service.GroupService;
import com.lucky.SwiftLinkMaster.admin.toolkit.RandomGenerator;
import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/23 16:53
 * @Description: 简链通分组服务实现层
 * @Position: com.lucky.SwiftLinkMaster.admin.service.impl
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */
@Slf4j
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {
    /**
     * 新增短链接分组
     * @param groupName
     */
    @Override
    public void saveGroup(String groupName) {
        // 生成符合条件的 gid
        String gid ;
        do {
            gid = RandomGenerator.generateRandom();
        } while (!hasGid(gid));
        GroupDO groupDO = GroupDO.builder()
                .gid(gid)
                .name(groupName)
                .build();
        baseMapper.insert(groupDO);
    }

    /**
     * 返回 True 说明是不存在的
     * @return
     */
    private boolean hasGid(String gid) {
        // 保证 gid和用户名 不重复，且是可用的，即是唯一索引，查询数据库不存在
        GroupDO hasGroupFlag = lambdaQuery().eq(GroupDO::getGid, gid)
                // TODO 设置用户名,不能通过用户名去传 username，否则会被调用接口刷，而是通过网关
                .eq(GroupDO::getUsername,null)
                .one();
        return hasGroupFlag == null;
    }
}
