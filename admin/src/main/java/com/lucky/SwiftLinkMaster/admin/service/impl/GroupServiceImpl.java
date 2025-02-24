package com.lucky.SwiftLinkMaster.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.SwiftLinkMaster.admin.common.biz.user.UserContext;
import com.lucky.SwiftLinkMaster.admin.dao.entity.GroupDO;
import com.lucky.SwiftLinkMaster.admin.dao.mapper.GroupMapper;
import com.lucky.SwiftLinkMaster.admin.dto.req.GroupUpdateReqDTO;
import com.lucky.SwiftLinkMaster.admin.dto.resp.GroupResponseDTO;
import com.lucky.SwiftLinkMaster.admin.service.GroupService;
import com.lucky.SwiftLinkMaster.admin.toolkit.RandomGenerator;
import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
        } while (!hasGid(gid)); // 不符合就继续生成
        GroupDO groupDO = GroupDO.builder()
                .gid(gid)
                .sortOrder(0)
                .username(UserContext.getUsername())
                .name(groupName)
                .build();
        baseMapper.insert(groupDO);
    }

    @Override
    public List<GroupResponseDTO> listGroup() {
        // 根据用户名获取存在的对应的已经创建的短链接组并按照排序优先级和更新时间倒序排序
        LambdaQueryWrapper<GroupDO> groupDOLambdaQueryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getDelFlag, 0)
                // TODO 获取用户名
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .orderByDesc(GroupDO::getSortOrder, GroupDO::getUpdateTime);
        List<GroupDO> groupDOList = baseMapper.selectList(groupDOLambdaQueryWrapper);
        return BeanUtil.copyToList(groupDOList,GroupResponseDTO.class);
    }

    @Override
    public void updateGroup(GroupUpdateReqDTO requestParam) {
        LambdaUpdateWrapper<GroupDO> updateWrapper = Wrappers.lambdaUpdate(GroupDO.class)
                .eq(GroupDO::getGid, requestParam.getGid())
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .eq(GroupDO::getDelFlag, 0);
        GroupDO groupDO = new GroupDO();
        groupDO.setName(requestParam.getName());
        baseMapper.update(groupDO,updateWrapper); // 根据用户名称和Gid和软删除，修改其分组名称为 groupDO 的分组名称

    }

    /**
     * 返回 True 说明是不存在的，符合条件，而不符合条件则返回 False
     * @return
     */
    private boolean hasGid(String gid) {
        // 保证 gid和用户名 不重复，且是可用的，即是唯一索引，查询数据库不存在
        GroupDO hasGroupFlag = lambdaQuery().eq(GroupDO::getGid, gid)
                // TODO 设置用户名,不能通过用户名去传 username，否则会被调用接口刷，而是通过网关
                .eq(GroupDO::getUsername,UserContext.getUsername())
                .one();
        return hasGroupFlag == null;
    }
}
