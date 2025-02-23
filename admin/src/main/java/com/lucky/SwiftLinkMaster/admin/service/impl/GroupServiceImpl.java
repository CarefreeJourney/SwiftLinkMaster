package com.lucky.SwiftLinkMaster.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.SwiftLinkMaster.admin.dao.entity.GroupDO;
import com.lucky.SwiftLinkMaster.admin.dao.mapper.GroupMapper;
import com.lucky.SwiftLinkMaster.admin.service.GroupService;
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
}
