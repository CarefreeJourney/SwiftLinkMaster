package com.lucky.SwiftLinkMaster.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.SwiftLinkMaster.admin.common.convention.exception.ClientException;
import com.lucky.SwiftLinkMaster.admin.common.enums.UserErrorCodeEnum;
import com.lucky.SwiftLinkMaster.admin.dao.entity.UserDO;
import com.lucky.SwiftLinkMaster.admin.dao.mapper.UserMapper;
import com.lucky.SwiftLinkMaster.admin.dto.resp.UserRespDTO;
import com.lucky.SwiftLinkMaster.admin.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/11 21:56
 * @Description: 用户接口实现层
 * @Position: com.lucky.SwiftLinkMaster.admin.service.impl
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {
    @Override
    public UserRespDTO getUserByUsername(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class).eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null){
            throw new ClientException(UserErrorCodeEnum.USER_NULL);
        }
        UserRespDTO result = new UserRespDTO();
        BeanUtils.copyProperties(userDO,result);
        return result;
    }
}
